package com.jfb.mercadolivro.infrastructure.sre.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class SreCounter(
    val metricName: String = "",
    val value: Double = 1.0,
    val labels: Array<String> = []
)
