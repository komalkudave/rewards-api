package com.rewards.rewards_api.service;

import com.rewards.rewards_api.dto.RewardResponse;
import com.rewards.rewards_api.entity.Customer;
import com.rewards.rewards_api.entity.Transaction;
import com.rewards.rewards_api.repository.CustomerRepository;
import com.rewards.rewards_api.repository.TransactionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    void shouldCalculateCustomerRewards() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John");

        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setCustomerId(1L);
        transaction1.setAmount(120.0);
        transaction1.setTransactionDate(LocalDate.of(2025, 10, 15));

        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setCustomerId(1L);
        transaction2.setAmount(75.0);
        transaction2.setTransactionDate(LocalDate.of(2025, 10, 20));

        when(customerRepository.findAll())
                .thenReturn(List.of(customer));

        when(transactionRepository.findByCustomerId(1L))
                .thenReturn(List.of(transaction1, transaction2));

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

        Assertions.assertEquals("October",
                response.getMonthlyRewards().get(0).getMonth());

        Assertions.assertEquals(115,
                response.getMonthlyRewards().get(0).getPoints());
    }
}