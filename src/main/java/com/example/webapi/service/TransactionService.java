package com.example.webapi.service;

import com.example.webapi.dto.RewardDto;
import com.example.webapi.entity.TransactionEntity;
import com.example.webapi.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class TransactionService {

    private static final BigDecimal FIRST_THRESHOLD = BigDecimal.valueOf(50);
    private static final BigDecimal SECOND_THRESHOLD = BigDecimal.valueOf(100);
    private static final int POINTS_OVER_100 = 2;
    private static final int POINTS_OVER_50 = 1;

    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        this.repository = repository;
    }

    public List<RewardDto> calculateRewards(String customerId, LocalDate start, LocalDate end) {
        List<TransactionEntity> transactions =
                repository.findByCustomerIdAndTransactionDateBetween(customerId, start, end);

        Map<String, RewardDto> monthlyRewards = new HashMap<>();

        for (TransactionEntity t : transactions) {
            int points = calculatePoints(t.getAmount());
            int month = t.getTransactionDate().getMonthValue();
            int year = t.getTransactionDate().getYear();
            String key = year + "-" + month;

            RewardDto dto = monthlyRewards.computeIfAbsent(key, k -> new RewardDto(customerId, customerId, month, year, 0, BigDecimal.ZERO));
            dto.setRewardPoints(dto.getRewardPoints() + points);
            dto.setTotalAmount(dto.getTotalAmount().add(t.getAmount()));
        }

        return new ArrayList<>(monthlyRewards.values());
    }

    private int calculatePoints(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return 0;
        }
        int points = 0;
        BigDecimal tempAmount = amount;
        if (tempAmount.compareTo(SECOND_THRESHOLD) > 0) {
            BigDecimal over100 = tempAmount.subtract(SECOND_THRESHOLD);
            points += over100.intValue() * POINTS_OVER_100;
            tempAmount = SECOND_THRESHOLD;
        }
        if (tempAmount.compareTo(FIRST_THRESHOLD) > 0) {
            BigDecimal over50 = tempAmount.subtract(FIRST_THRESHOLD);
            points += over50.intValue() * POINTS_OVER_50;
        }
        return points;
    }
}
