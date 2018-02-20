package io.github.ktalanda.maroubradomain

import io.github.ktalanda.maroubradomain.model.*
import io.github.ktalanda.maroubradomain.model.Direction.*

class RateCalculator {
    fun calculate(condition: Condition): Rate {

        val windSpeedRate = when(condition.wind.speed) {
            in 40..Int.MAX_VALUE -> 0.0
            in 20..40 -> 0.6
            in 0..3 -> 0.8
            else -> 1.0
        }
        val windDirectionRate = when(condition.wind.direction) {
            EAST, SOUTH_EAST, NORTH_EAST -> 0.8
            WEST, SOUTH_WEST, NORTH_WEST -> 1.0
            NORTH, SOUTH -> 0.9
        }

        val swellDirectionRate = when(condition.swell.direction) {
            EAST -> 1.0
            else -> 0.0
        }
        val swellSizeRate = when(condition.swell.height) {
            in 3..6 -> 1.0
            else -> 0.0
        }

        val rate = (swellSizeRate * swellDirectionRate * windSpeedRate * windDirectionRate * 100).toInt()

        return Rate(condition.time, when(rate) {
            0 -> RateDescription.NONE
            in Int.MIN_VALUE..15 -> RateDescription.NONE
            in 15..35 -> RateDescription.BAD
            in 35..60 -> RateDescription.AVERAGE
            in 60..85 -> RateDescription.GOOD
            in 85..Int.MAX_VALUE -> RateDescription.EPIC
            else -> RateDescription.UNDEFINED
        })
    }
}
