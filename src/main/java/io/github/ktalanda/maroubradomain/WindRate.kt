package io.github.ktalanda.maroubradomain

import io.github.ktalanda.maroubradomain.model.Direction
import io.github.ktalanda.maroubradomain.model.Wind

internal object WindRate {
    fun calculate(wind: Wind): Double {

        if (wind.speed < 1) return 0.7
        val windDirectionRate = when (wind.direction) {
            Direction.EAST, Direction.SOUTH_EAST, Direction.NORTH_EAST -> 0.5
            Direction.WEST, Direction.SOUTH_WEST, Direction.NORTH_WEST -> 1.0
            Direction.NORTH, Direction.SOUTH -> 0.9
        }
        val windSpeedRate = when (wind.speed) {
            in 40..Int.MAX_VALUE -> 0.0
            in 20..40 -> 0.6
            in 0..3 -> 0.9
            else -> 1.0
        }
        return windDirectionRate * windSpeedRate
    }
}