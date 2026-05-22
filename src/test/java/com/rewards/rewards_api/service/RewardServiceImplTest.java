package com.rewards.rewards_api.service;

import com.rewards.rewards_api.dto.RewardResponse;
import com.rewards.rewards_api.entity.Customer;
import com.rewards.rewards_api.entity.Transaction;
import com.rewards.rewards_api.repository.CustomerRepository;
import com.rewards.rewards_api.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Unit tests for RewardServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
class RewardServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardServiceImpl rewardService;

    @Test
    @DisplayName("Should calculate rewards for single customer")
    void shouldCalculateCustomerRewards() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John");

        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setCustomer(customer);
        transaction1.setAmount(120.0);
        transaction1.setTransactionDate(LocalDate.now().minusDays(10));

        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setCustomer(customer);
        transaction2.setAmount(75.0);
        transaction2.setTransactionDate(LocalDate.now().minusDays(5));

        when(customerRepository.findAll())
                .thenReturn(List.of(customer));

        when(transactionRepository.findByCustomerIdAndTransactionDateBetween(
                ArgumentMatchers.eq(customer.getId()),
                ArgumentMatchers.any(LocalDate.class),
                ArgumentMatchers.any(LocalDate.class)
        )).thenReturn(List.of(transaction1, transaction2));

        List<RewardResponse> responses =
                rewardService.getCustomerRewards();

        Assertions.assertEquals(1, responses.size());

        RewardResponse response = responses.get(0);

        Assertions.assertEquals("John",
                response.getCustomerName());

        Assertions.assertEquals(115,
                response.getTotalRewards());

        Assertions.assertEquals(1,
                response.getMonthlyRewards().size());

        Assertions.assertEquals(115,
                response.getMonthlyRewards().get(0).getPoints());
    }

    @Test
    @DisplayName("Should calculate rewards for multiple months")
    void shouldCalculateRewardsForMultipleMonths() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John");

        Transaction aprilTransaction = new Transaction();
        aprilTransaction.setId(1L);
        aprilTransaction.setCustomer(customer);
        aprilTransaction.setAmount(75.0);
        aprilTransaction.setTransactionDate(LocalDate.now().minusMonths(1));

        Transaction mayTransaction = new Transaction();
        mayTransaction.setId(2L);
        mayTransaction.setCustomer(customer);
        mayTransaction.setAmount(120.0);
        mayTransaction.setTransactionDate(LocalDate.now().minusDays(10));

        when(customerRepository.findAll())
                .thenReturn(List.of(customer));

        when(transactionRepository
                .findByCustomerIdAndTransactionDateBetween(
                        ArgumentMatchers.eq(customer.getId()),
                        ArgumentMatchers.any(LocalDate.class),
                        ArgumentMatchers.any(LocalDate.class)
                )).thenReturn(List.of(aprilTransaction, mayTransaction));

        List<RewardResponse> responses =
                rewardService.getCustomerRewards();

        Assertions.assertEquals(1, responses.size());

        RewardResponse response = responses.get(0);

        Assertions.assertEquals(115,
                response.getTotalRewards());

        Assertions.assertEquals(2,
                response.getMonthlyRewards().size());

        Assertions.assertTrue(
                response.getMonthlyRewards()
                        .stream()
                        .anyMatch(r ->
                                r.getPoints() == 25)
        );

        Assertions.assertTrue(
                response.getMonthlyRewards()
                        .stream()
                        .anyMatch(r ->
                                r.getPoints() == 90)
        );
    }

    @Test
    @DisplayName("Should return empty rewards when no customers exist")
    void shouldReturnEmptyRewardsWhenNoCustomersExist() {

        when(customerRepository.findAll())
                .thenReturn(List.of());

        List<RewardResponse> responses =
                rewardService.getCustomerRewards();

        Assertions.assertTrue(responses.isEmpty());
    }

    @Test
    @DisplayName("Should return zero rewards when customer has no transactions")
    void shouldReturnZeroRewardsWhenCustomerHasNoTransactions() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John");

        when(customerRepository.findAll())
                .thenReturn(List.of(customer));

        when(transactionRepository.findByCustomerIdAndTransactionDateBetween(
                ArgumentMatchers.eq(customer.getId()),
                ArgumentMatchers.any(LocalDate.class),
                ArgumentMatchers.any(LocalDate.class)
        )).thenReturn(List.of());

        List<RewardResponse> responses =
                rewardService.getCustomerRewards();

        Assertions.assertEquals(1, responses.size());

        RewardResponse response = responses.get(0);

        Assertions.assertEquals("John",
                response.getCustomerName());

        Assertions.assertEquals(0,
                response.getTotalRewards());

        Assertions.assertTrue(
                response.getMonthlyRewards().isEmpty()
        );
    }
}
