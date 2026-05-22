package com.rewards.rewards_api.controller;

import com.rewards.rewards_api.dto.RewardResponse;
import com.rewards.rewards_api.service.RewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
@Validated
@Tag(name = "Reward API",
        description = "APIs for customer reward calculations")
public class RewardController {

    private final RewardService rewardService;

    /**
     * Fetch reward details for all customers.
     *
     * @return list of customer rewards
     */
    @Operation(summary = "Get rewards for all customers")
    @GetMapping("/customers")
    public List<RewardResponse> getAllCustomerRewards() {

        return rewardService.getCustomerRewards();
    }

    /**
     * Fetch reward details for a specific customer.
     *
     * @param customerId customer id
     * @return customer reward response
     */
    @Operation(summary = "Get rewards by customer id")
    @GetMapping("/customers/{customerId}")
    public RewardResponse getRewardsByCustomerId(
            @PathVariable
            @Positive
            Long customerId) {

        return rewardService.getRewardsByCustomerId(customerId);
    }
}