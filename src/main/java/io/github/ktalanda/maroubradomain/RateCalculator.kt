package io.github.ktalanda.maroubradomain

import io.github.ktalanda.maroubradomain.model.*
import io.github.ktalanda.maroubradomain.model.Direction.*

class RateCalculator {
    fun calculate(condition: Condition): Rate {

        val swellRate = SwellRate.calculate(condition.swell)
        val windRate = WindRate.calculate(condition.wind)

        if (swellRate < 0.35) return Rate(condition.time, RateMapper.map(swellRate))
        if (windRate < 0.35) return Rate(condition.time, RateMapper.map(windRate))

        val rate = (swellRate + windRate) / 2

        return Rate(condition.time, RateMapper.map(rate))
    }
}
