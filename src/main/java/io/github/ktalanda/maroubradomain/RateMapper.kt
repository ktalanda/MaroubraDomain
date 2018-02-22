package io.github.ktalanda.maroubradomain

import io.github.ktalanda.maroubradomain.model.RateDescription

internal object RateMapper {
    fun map(value: Double): RateDescription {
        return when ((value * 100).toInt()) {
            0 -> RateDescription.NONE
            in Int.MIN_VALUE..15 -> RateDescription.NONE
            in 15..35 -> RateDescription.BAD
            in 35..60 -> RateDescription.AVERAGE
            in 60..85 -> RateDescription.GOOD
            in 85..Int.MAX_VALUE -> RateDescription.EPIC
            else -> RateDescription.UNDEFINED
        }
    }
}