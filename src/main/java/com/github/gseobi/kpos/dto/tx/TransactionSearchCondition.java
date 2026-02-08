package com.github.gseobi.kpos.dto.tx;

import com.github.gseobi.kpos.domain.enumtype.PaymentMethod;
import com.github.gseobi.kpos.domain.enumtype.TxStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Data
public class TransactionSearchCondition {
    private Long storeId;
    private PaymentMethod method;
    private TxStatus status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime from;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private OffsetDateTime to;

    private Long minAmount;
    private Long maxAmount;

    // txId/description/buyerId 부분검색
    private String keyword;
}
