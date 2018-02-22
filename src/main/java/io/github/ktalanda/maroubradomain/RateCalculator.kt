package io.github.ktalanda.maroubradomain

import io.github.ktalanda.maroubradomain.model.*
import io.github.ktalanda.maroubradomain.model.Direction.*

class RateCalculator {
    fun calculate(condition: Condition): Rate {

        val rate = (SwellRate.calculate(condition.swell) + WindRate.calculate(condition.wind)) / 2

        return Rate(condition.time, RateMapper.map(rate))
    }
}
