package com.rewards.rewards_api.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for RewardCalculator.
 */
class RewardCalculatorTest {

    @Test
    void shouldReturnZeroPointsForAmountLessThan50() {

        int points = RewardCalculator.calculateRewardPoints(40.0);

        Assertions.assertEquals(0, points);
    }

    @Test
    void shouldReturn25PointsFor75Dollars() {

        int points = RewardCalculator.calculateRewardPoints(75.0);

        Assertions.assertEquals(25, points);
    }

    @Test
    void shouldReturn90PointsFor120Dollars() {

        int points = RewardCalculator.calculateRewardPoints(120.0);

        Assertions.assertEquals(90, points);
    }

    @Test
    void shouldReturn250PointsFor200Dollars() {

        int points = RewardCalculator.calculateRewardPoints(200.0);

        Assertions.assertEquals(250, points);
    }

    @Test
    void shouldThrowExceptionForNegativeAmount() {

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> RewardCalculator.calculateRewardPoints(-10.0)
        );
    }
}