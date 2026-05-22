package com.rewards.rewards_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Integration tests for RewardController.
 */
@SpringBootTest
@AutoConfigureMockMvc
class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnCustomerRewards() throws Exception {

        mockMvc.perform(get("/api/rewards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].customerName")
                        .exists())
                .andExpect(jsonPath("$[0].totalRewards")
                        .exists());
    }
}