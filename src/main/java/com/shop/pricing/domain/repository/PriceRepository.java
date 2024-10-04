package com.shop.pricing.domain.repository;

import com.shop.pricing.domain.model.Price;

import java.time.LocalDateTime;

public interface PriceRepository {
    Price findPrice(Long brandId, Long productId, LocalDateTime date);
}
