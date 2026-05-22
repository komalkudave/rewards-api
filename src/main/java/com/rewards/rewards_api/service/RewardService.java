package com.rewards.rewards_api.service;

import com.rewards.rewards_api.dto.RewardResponse;

import java.util.List;

/**
 * Service interface for customer reward operations.
 */
public interface RewardService {

    /**
     * Fetch reward details for all customers.
     *
     * @return list of customer rewards
     */
    List<RewardResponse> getCustomerRewards();

    /**
     * Fetch reward details for a specific customer.
     *
     * @param customerId customer id
     * @return customer reward response
     */
    RewardResponse getRewardsByCustomerId(Long customerId);
}