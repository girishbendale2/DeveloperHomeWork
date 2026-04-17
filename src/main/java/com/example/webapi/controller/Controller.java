package com.example.webapi.controller;


import com.example.webapi.dto.RewardDto;
import com.example.webapi.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class Controller {

    private final TransactionService service;

    public Controller(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/{customerId}")
    public List<RewardDto> getRewards(
            @PathVariable String customerId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return service.calculateRewards(customerId, startDate, endDate);
    }
}
