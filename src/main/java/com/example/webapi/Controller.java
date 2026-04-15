package com.example.webapi.controller;


import org.springframework.web.bind.annotation.*;
import com.example.webapi.service.TransactionService;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class Controller {

    private final TransactionService service;

    public Controller(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/{customerId}")
    public Map<Month, Integer> getRewards(
            @PathVariable String customerId,
            @RequestParam String startDate,
            @RequestParam String endDate) {

        return service.calculateRewards(
                customerId,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate));
    }
}
