package com.shop.pricing.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceInfrastructureRequest {
    private Long brandId;
    private Long productId;
    private LocalDateTime currentTime;
}
