package com.rewards.rewards_api.controller;

import com.rewards.rewards_api.dto.RewardResponse;
import com.rewards.rewards_api.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for reward APIs.
 */
@RestController
@RequestMapping("/api/rewards")
@RequiredArgsConstructor
public class RewardController {

    private final RewardService rewardService;

    /**
     * Returns reward points for all customers.
     *
     * @return customer rewards
     */
    @GetMapping
    public List<RewardResponse> getCustomerRewards() {

        return rewardService.getCustomerRewards();
    }

    @GetMapping("/{customerId}")
    public RewardResponse getRewardsByCustomerId(
            @PathVariable Long customerId) {

        return rewardService.getRewardsByCustomerId(customerId);
    }
}