package com.example.webapi.repository;

import com.example.webapi.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByCustomerIdAndTransactionDateBetween(
            String customerId, LocalDate start, LocalDate end);
}
