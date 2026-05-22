package com.rewards.rewards_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO representing monthly reward points.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReward {

    /**
     * Month and year.
     */
    private String month;

    /**
     * Reward points earned in the month.
     */
    private Integer points;
}