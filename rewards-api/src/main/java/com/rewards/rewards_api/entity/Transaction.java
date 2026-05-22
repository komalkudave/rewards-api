package com.rewards.rewards_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


/**
 * Entity representing customer transactions.
 */
@Entity
@Table(name = "transactions")
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Double amount;
    private LocalDate transactionDate;
}
