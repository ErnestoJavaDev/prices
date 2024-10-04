package com.shop.pricing.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceServiceRequest {
    private Long brandId;
    private Long productId;
    private LocalDateTime currentTime;
}
