package com.github.gseobi.kpos.repository.query;

import com.github.gseobi.kpos.dto.tx.TransactionResponse;
import com.github.gseobi.kpos.dto.tx.TransactionSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransactionQueryRepository {
    Page<TransactionResponse> search(TransactionSearchCondition cond, Pageable pageable);
}
