package io.github.ktalanda.maroubradomain

import io.github.ktalanda.maroubradomain.model.Direction
import io.github.ktalanda.maroubradomain.model.Swell

internal object SwellRate {
    fun calculate(swell: Swell): Double {
        val swellDirectionRate = when (swell.direction) {
            Direction.EAST, Direction.NORTH_EAST, Direction.SOUTH_EAST -> 1.0
            Direction.NORTH, Direction.SOUTH -> 0.5
            else -> 0.0
        }
        val swellSizeRate = when (swell.height) {
            in 0.5..3.0 -> 0.5
            in 3..6 -> 1.0
            else -> 0.0
        }
        val swellPeriod = when (swell.period) {
            in 10..15 -> 1.0
            in 5..10 -> 0.7
            else -> 0.0
        }
        return swellDirectionRate * swellSizeRate * swellPeriod
    }
}