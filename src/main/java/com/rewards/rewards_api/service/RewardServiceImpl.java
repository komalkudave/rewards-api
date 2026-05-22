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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implementation for reward service.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RewardServiceImpl implements RewardService {

    private final CustomerRepository customerRepository;

    private final TransactionRepository transactionRepository;

    @Override
    public List<RewardResponse> getCustomerRewards() {

        log.info("Fetching rewards for all customers");

        List<Customer> customers = customerRepository.findAll();

        List<RewardResponse> rewardResponses = new ArrayList<>();

        for (Customer customer : customers) {

            rewardResponses.add(buildRewardResponse(customer));
        }

        log.info("Successfully fetched rewards for all customers");

        return rewardResponses;
    }

    @Override
    public RewardResponse getRewardsByCustomerId(Long customerId) {

        log.info("Fetching rewards for customer id: {}", customerId);

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> {

                    log.error("Customer not found with id: {}", customerId);

                    return new ResourceNotFoundException(
                            "Customer not found with id: " + customerId
                    );
                });

        RewardResponse rewardResponse = buildRewardResponse(customer);

        log.info("Successfully fetched rewards for customer id: {}",
                customerId);

        return rewardResponse;
    }

    /**
     * Builds reward response for a customer.
     *
     * @param customer customer details
     * @return reward response
     */
    private RewardResponse buildRewardResponse(Customer customer) {

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(3);

        List<Transaction> transactions =
                transactionRepository.findByCustomerIdAndTransactionDateBetween(
                        customer.getId(),
                        startDate,
                        endDate
                );

        Map<YearMonth, Integer> monthlyRewardMap = new TreeMap<>();

        int totalRewards = 0;

        for (Transaction transaction : transactions) {

            int points = RewardCalculator
                    .calculateRewardPoints(transaction.getAmount());

            YearMonth yearMonth =
                    YearMonth.from(transaction.getTransactionDate());

            monthlyRewardMap.put(
                    yearMonth,
                    monthlyRewardMap.getOrDefault(yearMonth, 0) + points
            );

            totalRewards += points;
        }

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMMM yyyy");

        List<MonthlyReward> monthlyRewards =
                monthlyRewardMap.entrySet()
                        .stream()
                        .map(entry ->
                                new MonthlyReward(
                                        entry.getKey().format(formatter),
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