package com.rewards.rewards_api.config;

import com.rewards.rewards_api.entity.Customer;
import com.rewards.rewards_api.entity.Transaction;
import com.rewards.rewards_api.repository.CustomerRepository;
import com.rewards.rewards_api.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

/**
 * Loads sample data into database.
 */
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(CustomerRepository customerRepository,
                               TransactionRepository transactionRepository) {


        return args -> {

            Customer customer1 = new Customer();
            customer1.setName("John");

            Customer customer2 = new Customer();
            customer2.setName("Alice");

            customerRepository.saveAll(List.of(customer1, customer2));

            Transaction t1 = new Transaction();
            t1.setCustomerId(customer1.getId());
            t1.setAmount(120.0);
            t1.setTransactionDate(LocalDate.of(2025, 10, 15));

            Transaction t2 = new Transaction();
            t2.setCustomerId(customer1.getId());
            t2.setAmount(75.0);
            t2.setTransactionDate(LocalDate.of(2025, 11, 10));

            Transaction t3 = new Transaction();
            t3.setCustomerId(customer2.getId());
            t3.setAmount(200.0);
            t3.setTransactionDate(LocalDate.of(2025, 12, 5));

            transactionRepository.saveAll(List.of(t1, t2, t3));

        };

    }
}