package io.github.ktalanda.maroubradomain

import io.github.ktalanda.maroubradomain.model.Direction
import io.github.ktalanda.maroubradomain.model.RateDescription
import io.github.ktalanda.maroubradomain.model.Wind
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

object WindRateTest: Spek ({
    describe("calculate") {
        given("light offshore") {
            it("should return epic range") {
                assertEquals(
                        RateDescription.EPIC,
                        RateMapper.map(WindRate.calculate(Wind(6, Direction.WEST))))
                assertEquals(
                        RateDescription.EPIC,
                        RateMapper.map(WindRate.calculate(Wind(6, Direction.NORTH_WEST))))
                assertEquals(
                        RateDescription.EPIC,
                        RateMapper.map(WindRate.calculate(Wind(6, Direction.SOUTH_WEST))))
            }
        }
        given("strong offshore") {
            it("should return epic range") {
                val windRateDescription = RateMapper.map(WindRate.calculate(Wind(20, Direction.WEST)))
                assertEquals(RateDescription.AVERAGE, windRateDescription)
            }
        }
        given("light onshore") {
            it("should return epic range") {
                val windRateDescription = RateMapper.map(WindRate.calculate(Wind(6, Direction.EAST)))
                assertEquals(RateDescription.AVERAGE, windRateDescription)
            }
        }
        given("strong onshore") {
            it("should return epic range") {
                val windRateDescription = RateMapper.map(WindRate.calculate(Wind(20, Direction.EAST)))
                assertEquals(RateDescription.BAD, windRateDescription)
            }
        }
        given("no wind") {
            it("should return epic range") {
                val windRateDescription = RateMapper.map(WindRate.calculate(Wind(0, Direction.EAST)))
                assertEquals(RateDescription.GOOD, windRateDescription)
            }
        }
        given("very strong wind") {
            it("should return none") {
                val windRateDescription = RateMapper.map(WindRate.calculate(Wind(50, Direction.EAST)))
                assertEquals(RateDescription.NONE, windRateDescription)
            }
        }
    }
})
