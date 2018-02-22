package io.github.ktalanda.maroubradomain

import io.github.ktalanda.maroubradomain.model.RateDescription
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.*

object RateMapperTest: Spek({
    describe("mapRate") {
        given("very small rate") {
            it("should return NONE") {
                assertEquals(RateDescription.NONE, RateMapper.map(0.1))
            }
        }
        given("small rate") {
            it("should return BAD") {
                assertEquals(RateDescription.BAD, RateMapper.map(0.3))
            }
        }
        given("medium rate") {
            it("should return AVERAGE") {
                assertEquals(RateDescription.AVERAGE, RateMapper.map(0.5))
            }
        }
        given("high rate") {
            it("should return GOOD") {
                assertEquals(RateDescription.GOOD, RateMapper.map(0.7))
            }
        }
        given("very high rate") {
            it("should return EPIC") {
                assertEquals(RateDescription.EPIC, RateMapper.map(0.9))
            }
        }
    }
})