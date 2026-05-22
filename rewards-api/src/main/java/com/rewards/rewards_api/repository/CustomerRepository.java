package com.rewards.rewards_api.repository;

import com.rewards.rewards_api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for customer operations.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}