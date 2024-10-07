package com.shop.pricing.infrastructure.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity {
    @Id
    @GeneratedValue
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
