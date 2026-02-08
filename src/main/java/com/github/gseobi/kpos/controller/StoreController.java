package com.github.gseobi.kpos.controller;

import com.github.gseobi.kpos.dto.store.StoreCreateRequest;
import com.github.gseobi.kpos.dto.store.StoreResponse;
import com.github.gseobi.kpos.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/create")
    public ResponseEntity<StoreResponse> create(@Valid @RequestBody StoreCreateRequest req) {
        log.info("Call [stores/create] API: {}", req);
        return ResponseEntity.ok(storeService.create(req));
    }
}
