package com.github.gseobi.kpos.dto.store;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StoreCreateRequest {
    @NotBlank private String storeCode;
    @NotBlank private String name;
    @NotBlank private String status;
}
