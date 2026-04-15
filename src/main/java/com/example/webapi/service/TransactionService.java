package com.example.webapi.service;

import com.example.webapi.entity.TransactionEntity;
import com.example.webapi.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.*;
import java.time.LocalDate;

@Service
public class TransactionService {

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public Map<Month, Integer> calculateRewards(String customerId, LocalDate start, LocalDate end) {
        List<TransactionEntity> transactions =
                repository.findByCustomerIdAndTransactionDateBetween(customerId, start, end);

        Map<Month, Integer> monthly = new HashMap<>();

        for (TransactionEntity t : transactions) {
            int points = calculatePoints(t.getAmount());
            Month month = t.getTransactionDate().getMonth();
            monthly.merge(month, points, Integer::sum);
        }
        return monthly;
    }

    private int calculatePoints(Double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
            amount = 100.0;
        }
        if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }
}
