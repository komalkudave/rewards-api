package com.rewards.rewards_api.util;

/**
 * Utility class for reward point calculation.
 */
public final class RewardCalculator {

    private static final int FIRST_REWARD_LIMIT = 50;

    private static final int SECOND_REWARD_LIMIT = 100;

    private static final int ONE_POINT = 1;

    private static final int TWO_POINTS = 2;

    /**
     * Private constructor to prevent instantiation.
     */
    private RewardCalculator() {
    }

    /**
     * Calculates reward points for a transaction amount.
     *
     * <p>
     * Rules:
     * <ul>
     *     <li>2 points for every dollar spent above $100</li>
     *     <li>1 point for every dollar spent between $50 and $100</li>
     * </ul>
     * </p>
     *
     * @param amount transaction amount
     * @return reward points
     */
    public static int calculateRewardPoints(double amount) {

        if (amount < 0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative");
        }

        int points = 0;

        if (amount > SECOND_REWARD_LIMIT) {

            points += (int) ((amount - SECOND_REWARD_LIMIT) * TWO_POINTS);

            points += (SECOND_REWARD_LIMIT - FIRST_REWARD_LIMIT) * ONE_POINT;

        } else if (amount > FIRST_REWARD_LIMIT) {

            points += (int) (amount - FIRST_REWARD_LIMIT);
        }

        return points;
    }
}