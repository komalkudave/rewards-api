package com.rewards.rewards_api.service;

import com.rewards.rewards_api.dto.MonthlyReward;
import com.rewards.rewards_api.dto.RewardResponse;
import com.rewards.rewards_api.entity.Customer;
import com.rewards.rewards_api.entity.Transaction;
import com.rewards.rewards_api.exception.ResourceNotFoundException;
import com.rewards.rewards_api.repository.CustomerRepository;
import com.rewards.rewards_api.repository.TransactionRepository;
import com.rewards.rewards_api.util.RewardCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.TextStyle;
import java.util.*;

/**
 * Implementation for reward service.
 */
@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final CustomerRepository customerRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public List<RewardResponse> getCustomerRewards() {

        List<Customer> customers = customerRepository.findAll();

        List<RewardResponse> rewardResponses = new ArrayList<>();

        for (Customer customer : customers) {

            List<Transaction> transactions =
                    transactionRepository.findByCustomerId(customer.getId());

            Map<String, Integer> monthlyRewardMap = new HashMap<>();

            int totalRewards = 0;

            for (Transaction transaction : transactions) {

                int points = RewardCalculator
                        .calculateRewardPoints(transaction.getAmount());

                String month = transaction.getTransactionDate()
                        .getMonth()
                        .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

                monthlyRewardMap.put(
                        month,
                        monthlyRewardMap.getOrDefault(month, 0) + points
                );

                totalRewards += points;
            }

            List<MonthlyReward> monthlyRewards =
                    monthlyRewardMap.entrySet()
                            .stream()
                            .map(entry ->
                                    new MonthlyReward(
                                            entry.getKey(),
                                            entry.getValue()
                                    )
                            )
                            .toList();

            RewardResponse response = new RewardResponse(
                    customer.getId(),
                    customer.getName(),
                    monthlyRewards,
                    totalRewards
            );

            rewardResponses.add(response);
        }

        return rewardResponses;
    }


    @Override
    public RewardResponse getRewardsByCustomerId(Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found with id: " + customerId
                        )
                );

        List<Transaction> transactions =
                transactionRepository.findByCustomerId(customerId);

        Map<String, Integer> monthlyRewardMap = new HashMap<>();

        int totalRewards = 0;

        for (Transaction transaction : transactions) {

            int points = RewardCalculator
                    .calculateRewardPoints(transaction.getAmount());

            String month = transaction.getTransactionDate()
                    .getMonth()
                    .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

            monthlyRewardMap.put(
                    month,
                    monthlyRewardMap.getOrDefault(month, 0) + points
            );

            totalRewards += points;
        }

        List<MonthlyReward> monthlyRewards =
                monthlyRewardMap.entrySet()
                        .stream()
                        .map(entry ->
                                new MonthlyReward(
                                        entry.getKey(),
                                        entry.getValue()
                                )
                        )
                        .toList();

        return new RewardResponse(
                customer.getId(),
                customer.getName(),
                monthlyRewards,
                totalRewards
        );
    }


}