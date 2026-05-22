package com.rewards.rewards_api.repository;

import com.rewards.rewards_api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for transaction operations.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByCustomerId(Long customerId);
}