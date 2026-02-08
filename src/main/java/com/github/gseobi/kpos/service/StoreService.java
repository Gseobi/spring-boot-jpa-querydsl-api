package com.github.gseobi.kpos.service;

import com.github.gseobi.kpos.domain.Store;
import com.github.gseobi.kpos.dto.store.StoreCreateRequest;
import com.github.gseobi.kpos.dto.store.StoreResponse;
import com.github.gseobi.kpos.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public StoreResponse create(StoreCreateRequest req) {
        Store store = Store.builder()
                .storeCode(req.getStoreCode())
                .name(req.getName())
                .status(req.getStatus())
                .build();
        log.info("Store created: {}", store);

        Store saved = this.storeRepository.save(store);
        log.info("Store saved: {}", saved);

        return StoreResponse.builder()
                .id(saved.getId())
                .storeCode(saved.getStoreCode())
                .name(saved.getName())
                .status(saved.getStatus())
                .build();
    }
}
