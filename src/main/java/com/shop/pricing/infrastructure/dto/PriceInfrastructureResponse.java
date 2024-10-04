package com.shop.pricing.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceInfrastructureResponse {
    private Long brandId;
    private Long productId;
    private Integer priceList;
    private Double price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
