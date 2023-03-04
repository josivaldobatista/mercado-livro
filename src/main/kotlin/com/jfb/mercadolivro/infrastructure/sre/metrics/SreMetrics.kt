package com.jfb.mercadolivro.infrastructure.sre.metrics

import org.springframework.stereotype.Component
import java.time.Duration

@Component
interface SreMetrics {

    fun measureHistogram(
        metricName: String,
        observableTime: Duration,
        buckets: DoubleArray? = null,
        labels: Array<String>? = null
    )

    fun measureCounter(
        metricName: String,
        value: Double = 1.0,
        labels: Array<String>? = null
    )
}