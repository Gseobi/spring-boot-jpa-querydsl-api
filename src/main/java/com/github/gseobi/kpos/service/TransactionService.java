package com.github.gseobi.kpos.service;

import com.github.gseobi.kpos.domain.PaymentTransaction;
import com.github.gseobi.kpos.domain.Store;
import com.github.gseobi.kpos.domain.Terminal;
import com.github.gseobi.kpos.domain.enumtype.TxStatus;
import com.github.gseobi.kpos.dto.tx.TransactionCreateRequest;
import com.github.gseobi.kpos.dto.tx.TransactionResponse;
import com.github.gseobi.kpos.dto.tx.TransactionSearchCondition;
import com.github.gseobi.kpos.repository.StoreRepository;
import com.github.gseobi.kpos.repository.TerminalRepository;
import com.github.gseobi.kpos.repository.TransactionRepository;
import com.github.gseobi.kpos.repository.query.TransactionQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final StoreRepository storeRepository;
    private final TerminalRepository terminalRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionQueryRepository transactionQueryRepository;

    @Transactional
    public TransactionResponse create(TransactionCreateRequest req) {
        Store store = this.storeRepository.findById(req.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("STORE_NOT_FOUND"));
        log.info("Store created: {}", store);

        Terminal terminal = null;
        if (req.getTerminalId() != null) {
            terminal = this.terminalRepository.findById(req.getTerminalId())
                    .orElseThrow(() -> new IllegalArgumentException("TERMINAL_NOT_FOUND"));
        }
        log.info("Terminal created: {}", terminal);

        PaymentTransaction tx = PaymentTransaction.builder()
                .txId(req.getTxId())
                .store(store)
                .terminal(terminal)
                .amount(req.getAmount())
                .currency(req.getCurrency())
                .method(req.getMethod())
                .status(TxStatus.REQUESTED)
                .buyerId(req.getBuyerId())
                .description(req.getDescription())
                .requestedAt(OffsetDateTime.now())
                .build();
        log.info("Payment Transaction created: {}", tx);

        PaymentTransaction saved = this.transactionRepository.save(tx);
        log.info("Payment Transaction saved: {}", saved);

        return TransactionResponse.builder()
                .txId(saved.getTxId())
                .storeId(store.getId())
                .terminalId(terminal != null ? terminal.getId() : null)
                .amount(saved.getAmount())
                .currency(saved.getCurrency())
                .method(saved.getMethod())
                .status(saved.getStatus())
                .buyerId(saved.getBuyerId())
                .description(saved.getDescription())
                .requestedAt(saved.getRequestedAt())
                .approvedAt(saved.getApprovedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public Page<TransactionResponse> search(TransactionSearchCondition cond, Pageable pageable) {
        return this.transactionQueryRepository.search(cond, pageable);
    }
}
