package com.rewards.rewards_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO representing customer reward response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardResponse {

    private Long customerId;

    private String customerName;

    private List<MonthlyReward> monthlyRewards;

    private Integer totalRewards;
}