package com.rewards.rewards_api.util;

/**
 * Utility class for reward point calculation.
 */
public class RewardCalculator {

    /**
     * Calculates reward points for a transaction amount.
     *
     * @param amount transaction amount
     * @return reward points
     */
    public static int calculateRewardPoints(Double amount) {

        if (amount == null || amount < 0) {
            throw new IllegalArgumentException("Amount cannot be null or negative");
        }

        int points = 0;

        if (amount > 100) {
            points += (int) ((amount - 100) * 2);
            points += 50;
        } else if (amount > 50) {
            points += (int) (amount - 50);
        }

        return points;
    }
}