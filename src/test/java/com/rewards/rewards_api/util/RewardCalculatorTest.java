package com.rewards.rewards_api.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for RewardCalculator.
 */
class RewardCalculatorTest {

    @Test
    void shouldReturnZeroPointsForAmountLessThan50() {

        int points = RewardCalculator.calculateRewardPoints(40);

        Assertions.assertEquals(0, points);
    }

    @Test
    void shouldReturnOnePointPerDollarBetween50And100() {

        int points = RewardCalculator.calculateRewardPoints(75);

        Assertions.assertEquals(25, points);
    }

    @Test
    void shouldReturnCorrectPointsForAmountAbove100() {

        int points = RewardCalculator.calculateRewardPoints(120);

        Assertions.assertEquals(90, points);
    }

    @Test
    void shouldThrowExceptionForNegativeAmount() {

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> RewardCalculator.calculateRewardPoints(-50)
        );
    }

    @Test
    void shouldReturnZeroPointsFor50Dollars() {

        int points = RewardCalculator.calculateRewardPoints(50.0);

        Assertions.assertEquals(0, points);
    }

    @Test
    void shouldReturnOnePointFor51Dollars() {

        int points = RewardCalculator.calculateRewardPoints(51.0);

        Assertions.assertEquals(1, points);
    }

    @Test
    void shouldReturn50PointsFor100Dollars() {

        int points = RewardCalculator.calculateRewardPoints(100.0);

        Assertions.assertEquals(50, points);
    }

    @Test
    void shouldReturn52PointsFor101Dollars() {

        int points = RewardCalculator.calculateRewardPoints(101.0);

        Assertions.assertEquals(52, points);
    }


}