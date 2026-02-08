package com.github.gseobi.kpos.dto.tx;

import com.github.gseobi.kpos.domain.enumtype.PaymentMethod;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionCreateRequest {
    @NotBlank private String txId;

    @NotNull private Long storeId;
    private Long terminalId;

    @Min(1) private long amount;
    @NotBlank private String currency;

    @NotNull private PaymentMethod method;

    private String buyerId;
    private String description;
}
