package com.jfb.mercadolivro.infrastructure.sre.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SreHistogram(
    val metricName: String = "",
    val buckets: DoubleArray = [],
    val labels: Array<String> = []
)
