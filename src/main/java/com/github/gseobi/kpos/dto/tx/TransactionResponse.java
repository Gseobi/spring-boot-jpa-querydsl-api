package com.github.gseobi.kpos.dto.tx;

import com.github.gseobi.kpos.domain.enumtype.PaymentMethod;
import com.github.gseobi.kpos.domain.enumtype.TxStatus;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class TransactionResponse {
    private String txId;
    private Long storeId;
    private Long terminalId;
    private long amount;
    private String currency;
    private PaymentMethod method;
    private TxStatus status;
    private String buyerId;
    private String description;
    private OffsetDateTime requestedAt;
    private OffsetDateTime approvedAt;
}
