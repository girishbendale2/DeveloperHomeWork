package com.example.webapi.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;
    private Double amount;
    private LocalDate transactionDate;

    public TransactionEntity() {}

    public TransactionEntity(String customerId, Double amount, LocalDate transactionDate) {
        this.customerId = customerId;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Long getId() { return id; }
    public String getCustomerId() { return customerId; }
    public Double getAmount() { return amount; }
    public LocalDate getTransactionDate() { return transactionDate; }
}
