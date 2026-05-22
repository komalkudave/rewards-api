package com.rewards.rewards_api.repository;

import com.rewards.rewards_api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for customer operations.
 */
@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

}