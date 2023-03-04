package com.jfb.mercadolivro.infrastructure.sre.aspect

import com.jfb.mercadolivro.infrastructure.sre.annotations.SreCounter
import com.jfb.mercadolivro.infrastructure.sre.annotations.SreHistogram
import com.jfb.mercadolivro.infrastructure.sre.metrics.SreMetrics
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.reflect.UndeclaredThrowableException
import java.time.Duration
import java.time.Instant

@Aspect
@Component
class SreMicrometerAspect(private val sreMetrics: SreMetrics) {

    private val logger: Logger = LoggerFactory.getLogger(SreMicrometerAspect::class.java)

    @Pointcut("within(@com.jfb.mercadolivro.infrastructure.sre.annotations.EnableSreMetrics *)")
    fun repositoryClassMethods() {
    }

    @Around("repositoryClassMethods()")
    @Throws(Exception::class)
    fun measureMethodExecutionTime(pjp: ProceedingJoinPoint): Any? {
        val annotation: Array<Annotation>

        try {
            val methodSignature: MethodSignature = pjp.signature as MethodSignature
            annotation = methodSignature.method.annotations
        } catch (exception: Exception) {
            logger.warn("Unexpected error when processing method annotation", exception)
            return pjp.proceed()
        }

        val methodName = pjp.signature.name
        val start = Instant.now()

        try {
            val retVal = pjp.proceed()
            val elapsedTime = Duration.between(start, Instant.now())
            logger.debug("Execution of $methodName took ${elapsedTime.toMillis().toDouble()}")

            for (a in annotation) {
                when (a) {
                    is SreHistogram -> sreMetrics.measureHistogram(
                        getMetricName(a.metricName, methodName),
                        elapsedTime,
                        a.buckets,
                        a.labels
                    )

                    is SreCounter -> sreMetrics.measureCounter(
                        getMetricName(a.metricName, methodName),
                        a.value,
                        a.labels
                    )
                }
            }
            return retVal
        } catch (exception: UndeclaredThrowableException) {
            throw RuntimeException(exception.undeclaredThrowable)
        } catch (exception: RuntimeException) {
            throw exception
        }
    }

    private fun getMetricName(metricName: String, methodName: String): String {
        return metricName.takeIf { metricName.isNotBlank() } ?: methodName.toSnakCase()
    }
}

private fun String.toSnakCase(): String {
    return this.map {
        if (it.isUpperCase()) {
            "_${it.toLowerCase()}"
        } else {
            "$it"
        }
    }.joinToString(separator = "")
}