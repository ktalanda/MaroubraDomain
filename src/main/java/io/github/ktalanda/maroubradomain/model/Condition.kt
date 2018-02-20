package io.github.ktalanda.maroubradomain.model

import java.util.Date

data class Condition (
        val time: Date,
        val swell: Swell,
        val wind: Wind,
        val tide: Tide
)
