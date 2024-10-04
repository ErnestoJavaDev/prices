package com.shop.pricing.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    @Id
    private UUID id;
    private Long brandId;
    private Long productId;
    private Integer priceList;
    private Integer priority;
    private Double price;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
