package com.rewards.rewards_api.repository;

import com.rewards.rewards_api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for transaction operations.
 */
@Repository
public interface TransactionRepository
        extends JpaRepository<Transaction, Long> {

    /**
     * Fetch all transactions for a customer.
     *
     * @param customerId customer id
     * @return list of transactions
     */
    List<Transaction> findByCustomerId(Long customerId);

    /**
     * Fetch transactions for a customer
     * between the given date range.
     *
     * @param customerId customer id
     * @param startDate  start date
     * @param endDate    end date
     * @return list of transactions
     */
    List<Transaction> findByCustomerIdAndTransactionDateBetween(
            Long customerId,
            LocalDate startDate,
            LocalDate endDate
    );
}