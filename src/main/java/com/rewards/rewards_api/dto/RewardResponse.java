package com.rewards.rewards_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * DTO representing customer reward response.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RewardResponse {

    /**
     * Customer id.
     */
    private Long customerId;

    /**
     * Customer name.
     */
    private String customerName;

    /**
     * Monthly reward details.
     */
    private List<MonthlyReward> monthlyRewards;

    /**
     * Total reward points.
     */
    private Integer totalRewards;
}