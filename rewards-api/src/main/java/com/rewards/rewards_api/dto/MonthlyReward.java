package com.rewards.rewards_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representing monthly reward points.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReward {

    private String month;

    private Integer points;
}