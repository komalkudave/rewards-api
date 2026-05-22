package com.rewards.rewards_api.entity;


import jakarta.persistence.*;
import lombok.Data;

/**
 * Entity representing customer information.
 */

@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
