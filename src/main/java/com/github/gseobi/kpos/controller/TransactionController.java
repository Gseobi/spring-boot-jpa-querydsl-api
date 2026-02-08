package com.github.gseobi.kpos.controller;

import com.github.gseobi.kpos.dto.tx.TransactionCreateRequest;
import com.github.gseobi.kpos.dto.tx.TransactionResponse;
import com.github.gseobi.kpos.dto.tx.TransactionSearchCondition;
import com.github.gseobi.kpos.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/create")
    public ResponseEntity<TransactionResponse> create(@Valid @RequestBody TransactionCreateRequest req) {
        log.info("Call [transactions/create] API: {}", req);
        return ResponseEntity.ok(transactionService.create(req));
    }

    // Example: /transactions?storeId=1&status=APPROVED&from=2026-02-01T00:00:00+09:00&keyword=abc
    @GetMapping("/search")
    public ResponseEntity<Page<TransactionResponse>> search(TransactionSearchCondition cond, Pageable pageable) {
        log.info("Call [transactions/search] API: cond={}, pageable={}", cond, pageable);
        return ResponseEntity.ok(transactionService.search(cond, pageable));
    }
}
