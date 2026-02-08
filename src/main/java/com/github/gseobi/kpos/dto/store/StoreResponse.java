package com.github.gseobi.kpos.dto.store;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreResponse {
    private Long id;
    private String storeCode;
    private String name;
    private String status;
}
