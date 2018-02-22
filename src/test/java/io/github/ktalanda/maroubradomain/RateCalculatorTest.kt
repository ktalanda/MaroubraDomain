package io.github.ktalanda.maroubradomain

import io.github.ktalanda.maroubradomain.model.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

import org.junit.jupiter.api.Assertions.*
import java.util.*

object RateCalculatorTest : Spek({
    val rateCalculator = RateCalculator()

    val bigSwell = 5.0
    val bigPeriod = 14.0
    val lightWindSpeed = 10
    val strongWindSpeed = 20

    given("Strong wind.") {
        val condition = Condition(
                Date(),
                Swell(3.0, Direction.EAST, 10.0),
                Wind(100, Direction.EAST),
                Tide.RISING
        )
        it("should rate NONE.") {
            assertEquals(
                    RateDescription.NONE,
                    rateCalculator.calculate(condition).description)
        }
    }
    given("Huge swell"){
        val condition = Condition(
                Date(),
                Swell(20.0, Direction.EAST, 10.0),
                Wind(10, Direction.EAST),
                Tide.RISING
        )
        it("should rate NONE.") {
            assertEquals(
                    RateDescription.NONE,
                    rateCalculator.calculate(condition).description)
        }
    }
    given("Big swell.") {
        given("Big period.") {
            given("From EAST") {
                val greatSwell = Swell(bigSwell, Direction.EAST, bigPeriod)
                given("Light offshore") {
                    it("should rate epic") {
                        assertEquals(
                                RateDescription.EPIC,
                                rateCalculator.calculate(Condition(Date(), greatSwell, Wind(lightWindSpeed, Direction.WEST), Tide.RISING)).description)
                        assertEquals(
                                RateDescription.EPIC,
                                rateCalculator.calculate(Condition(Date(), greatSwell, Wind(lightWindSpeed, Direction.SOUTH_WEST), Tide.RISING)).description)
                        assertEquals(
                                RateDescription.EPIC,
                                rateCalculator.calculate(Condition(Date(), greatSwell, Wind(lightWindSpeed, Direction.NORTH_WEST), Tide.RISING)).description)
                    }
                }
                given("Strong offshore") {
                    it("should rate good") {
                        assertEquals(
                                RateDescription.GOOD,
                                rateCalculator.calculate(Condition(Date(), greatSwell, Wind(strongWindSpeed, Direction.WEST), Tide.RISING)).description)
                        assertEquals(
                                RateDescription.GOOD,
                                rateCalculator.calculate(Condition(Date(), greatSwell, Wind(strongWindSpeed, Direction.SOUTH_WEST), Tide.RISING)).description)
                        assertEquals(
                                RateDescription.GOOD,
                                rateCalculator.calculate(Condition(Date(), greatSwell, Wind(strongWindSpeed, Direction.NORTH_WEST), Tide.RISING)).description)
                    }
                }
                given("Light onshore") {
                    it("should rate good") {
                        assertEquals(
                                RateDescription.GOOD,
                                rateCalculator.calculate(Condition(Date(), greatSwell, Wind(lightWindSpeed, Direction.EAST), Tide.RISING)).description)
                        assertEquals(
                                RateDescription.GOOD,
                                rateCalculator.calculate(Condition(Date(), greatSwell, Wind(lightWindSpeed, Direction.SOUTH_EAST), Tide.RISING)).description)
                        assertEquals(
                                RateDescription.GOOD,
                                rateCalculator.calculate(Condition(Date(), greatSwell, Wind(lightWindSpeed, Direction.NORTH_EAST), Tide.RISING)).description)
                    }
                }
                given("Strong onshore") {
                    it("should rate bad") {
                        assertEquals(
                                RateDescription.BAD,
                                rateCalculator.calculate(Condition(Date(), greatSwell, Wind(strongWindSpeed, Direction.EAST), Tide.RISING)).description)
                        assertEquals(
                                RateDescription.BAD,
                                rateCalculator.calculate(Condition(Date(), greatSwell, Wind(strongWindSpeed, Direction.SOUTH_EAST), Tide.RISING)).description)
                        assertEquals(
                                RateDescription.BAD,
                                rateCalculator.calculate(Condition(Date(), greatSwell, Wind(strongWindSpeed, Direction.NORTH_EAST), Tide.RISING)).description)
                    }
                }
            }
        }
    }
})
