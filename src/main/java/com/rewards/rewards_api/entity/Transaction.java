package com.rewards.rewards_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity representing customer transactions.
 */
@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {

    /**
     * Transaction id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Customer associated with transaction.
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * Transaction amount.
     */
    @NotNull(message = "Transaction amount cannot be null")
    @Positive(message = "Transaction amount must be positive")
    @Column(nullable = false)
    private Double amount;

    /**
     * Transaction date.
     */
    @NotNull(message = "Transaction date cannot be null")
    @Column(name = "transaction_date", nullable = false)
    private LocalDate transactionDate;
}