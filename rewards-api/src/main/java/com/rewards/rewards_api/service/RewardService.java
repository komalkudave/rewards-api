package com.rewards.rewards_api.service;

import com.rewards.rewards_api.dto.RewardResponse;

import java.util.List;

/**
 * Service interface for reward calculation.
 */
public interface RewardService {

    RewardResponse getRewardsByCustomerId(Long customerId);

    List<RewardResponse> getCustomerRewards();
}