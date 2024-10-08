package com.shop.pricing.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    private Long id;
    private Long brandId;
    private Long productId;
    private Integer priceList;
    private Integer priority;
    private Double price;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
