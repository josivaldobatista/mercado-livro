package com.jfb.mercadolivro.infrastructure.sre.metrics

import io.micrometer.core.instrument.Counter
import io.micrometer.core.instrument.DistributionSummary
import io.micrometer.core.instrument.Metrics
import org.springframework.stereotype.Component
import java.time.Duration

@Component
object SreMicrometer : SreMetrics {

    private val defaultSla = doubleArrayOf(
        100.0,
        120.0,
        150.0,
        180.0,
        200.0,
        220.0,
        250.0,
        280.0,
        300.0,
        320.0,
        350.0,
        380.0,
        400.0,
        420.0,
        450.0,
        480.0,
        500.0,
        520.0,
        550.0,
        580.0,
        600.0,
        620.0,
        650.0,
        680.0,
        700.0,
        720.0,
        750.0,
        780.0,
        800.0,
        820.0,
        850.0,
        880.0,
        900.0,
        920.0,
        950.0,
        980.0,
        1000.0,
        1200.0,
        1500.0,
        1800.0,
        2000.0,
        2200.0,
        2500.0,
        2800.0,
        3000.0,
        3200.0,
        3500.0,
        3800.0,
        4000.0,
        4200.0,
        4500.0,
        4800.0,
        5000.0,
    )

    override fun measureHistogram(
        metricName: String,
        observableTime: Duration,
        buckets: DoubleArray?,
        labels: Array<String>?
    ) {
        val sla = if (buckets != null && buckets.isNotEmpty()) {
            buckets
        } else {
            defaultSla
        }

        DistributionSummary.builder(metricName)
            .publishPercentileHistogram()
            .maximumExpectedValue(sla.maxOrNull())
            .sla(*sla)
            .also {
                if (labels != null) {
                    it.tags(*labels)
                }
            }
            .register(Metrics.globalRegistry)
            .also {
                it.record(observableTime.toMillis().toDouble())
            }
    }

    override fun measureCounter(metricName: String, value: Double, labels: Array<String>?) {
        Counter.builder(metricName)
            .also {
                if (labels != null) {
                    it.tags(*labels)
                }
            }
            .register(Metrics.globalRegistry)
            .also {
                it.increment(value)
            }
    }
}