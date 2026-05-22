package com.rewards.rewards_api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing customer information.
 */
@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer {

    /**
     * Customer id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Customer name.
     */
    @NotBlank(message = "Customer name cannot be blank")
    @Column(nullable = false)
    private String name;

    /**
     * Customer transactions.
     */
    @JsonManagedReference
    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Transaction> transactions = new ArrayList<>();
}