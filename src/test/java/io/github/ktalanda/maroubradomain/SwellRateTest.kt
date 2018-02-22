package io.github.ktalanda.maroubradomain

import io.github.ktalanda.maroubradomain.model.Direction
import io.github.ktalanda.maroubradomain.model.RateDescription
import io.github.ktalanda.maroubradomain.model.Swell
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertEquals

object SwellRateTest : Spek({
    describe("calculate") {
        given("big") {
            given("high period") {
                given("from east") {
                    it("should return EPIC") {
                        assertEquals(
                                RateDescription.EPIC,
                                RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.EAST, 12.0))))
                        assertEquals(
                                RateDescription.EPIC,
                                RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.SOUTH_EAST, 12.0))))
                        assertEquals(
                                RateDescription.EPIC,
                                RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.NORTH_EAST, 12.0))))
                    }
                }
                given("from south or north") {
                    it("should return AVERAGE") {
                        assertEquals(
                                RateDescription.AVERAGE,
                                RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.NORTH, 12.0))))
                        assertEquals(
                                RateDescription.AVERAGE,
                                RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.SOUTH, 12.0))))
                    }
                }
                given("from west") {
                    it("should return NONE") {
                        assertEquals(
                                RateDescription.NONE,
                                RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.WEST, 12.0))))
                        assertEquals(
                                RateDescription.NONE,
                                RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.SOUTH_WEST, 12.0))))
                        assertEquals(
                                RateDescription.NONE,
                                RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.NORTH_WEST, 12.0))))
                    }
                }
            }

            given("given small period") {
                given("high period") {
                    given("from east") {
                        it("should return EPIC") {
                            assertEquals(
                                    RateDescription.GOOD,
                                    RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.EAST, 7.0))))
                            assertEquals(
                                    RateDescription.GOOD,
                                    RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.SOUTH_EAST, 7.0))))
                            assertEquals(
                                    RateDescription.GOOD,
                                    RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.NORTH_EAST, 7.0))))
                        }
                    }
                    given("from south or north") {
                        it("should return AVERAGE") {
                            assertEquals(
                                    RateDescription.BAD,
                                    RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.NORTH, 7.0))))
                            assertEquals(
                                    RateDescription.BAD,
                                    RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.SOUTH, 7.0))))
                        }
                    }
                    given("from west") {
                        it("should return NONE") {
                            assertEquals(
                                    RateDescription.NONE,
                                    RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.WEST, 7.0))))
                            assertEquals(
                                    RateDescription.NONE,
                                    RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.SOUTH_WEST, 7.0))))
                            assertEquals(
                                    RateDescription.NONE,
                                    RateMapper.map(SwellRate.calculate(Swell(5.0, Direction.NORTH_WEST, 7.0))))
                        }
                    }
                }
            }
        }
        given("small") {
            it("should return AVERAGE") {
                assertEquals(
                        RateDescription.AVERAGE,
                        RateMapper.map(SwellRate.calculate(Swell(1.0, Direction.EAST, 10.0))))
            }
        }
        given("no swell") {
            it("should return NONE") {
                assertEquals(
                        RateDescription.NONE,
                        RateMapper.map(SwellRate.calculate(Swell(0.0, Direction.EAST, 10.0))))
            }
        }
        given("huge swell") {
            it("should return NONE") {
                assertEquals(
                        RateDescription.NONE,
                        RateMapper.map(SwellRate.calculate(Swell(20.0, Direction.EAST, 10.0))))
            }
        }
    }
})