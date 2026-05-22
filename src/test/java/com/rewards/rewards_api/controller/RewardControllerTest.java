package com.rewards.rewards_api.controller;

import com.rewards.rewards_api.dto.MonthlyReward;
import com.rewards.rewards_api.dto.RewardResponse;
import com.rewards.rewards_api.exception.ResourceNotFoundException;
import com.rewards.rewards_api.service.RewardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for RewardController.
 */
@WebMvcTest(RewardController.class)
class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService rewardService;

    @Test
    @DisplayName("Should return customer rewards successfully")
    void shouldReturnCustomerRewards() throws Exception {

        RewardResponse response = new RewardResponse(
                1L,
                "John",
                List.of(
                        new MonthlyReward("April 2026", 25),
                        new MonthlyReward("May 2026", 90)
                ),
                115
        );

        when(rewardService.getCustomerRewards())
                .thenReturn(List.of(response));

        mockMvc.perform(
                        get("/api/rewards/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$[0].customerId")
                        .value(1))

                .andExpect(jsonPath("$[0].customerName")
                        .value("John"))

                .andExpect(jsonPath("$[0].totalRewards")
                        .value(115));
    }

    @Test
    @DisplayName("Should return empty list when no rewards exist")
    void shouldReturnEmptyListWhenNoRewardsExist() throws Exception {

        when(rewardService.getCustomerRewards())
                .thenReturn(List.of());

        mockMvc.perform(
                        get("/api/rewards/customers")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.length()")
                        .value(0));
    }

    @Test
    @DisplayName("Should return rewards by customer id")
    void shouldReturnRewardsByCustomerId() throws Exception {

        RewardResponse response = new RewardResponse(
                1L,
                "John",
                List.of(
                        new MonthlyReward("May 2026", 90)
                ),
                90
        );

        when(rewardService.getRewardsByCustomerId(1L))
                .thenReturn(response);

        mockMvc.perform(
                        get("/api/rewards/customers/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.customerId")
                        .value(1))

                .andExpect(jsonPath("$.customerName")
                        .value("John"))

                .andExpect(jsonPath("$.totalRewards")
                        .value(90));
    }

    @Test
    @DisplayName("Should return 404 when customer not found")
    void shouldReturn404WhenCustomerNotFound() throws Exception {

        when(rewardService.getRewardsByCustomerId(99L))
                .thenThrow(
                        new ResourceNotFoundException(
                                "Customer not found"
                        )
                );

        mockMvc.perform(
                        get("/api/rewards/customers/99")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }
}