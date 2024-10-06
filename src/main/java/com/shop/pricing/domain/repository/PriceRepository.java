package com.shop.pricing.domain.repository;

import com.shop.pricing.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findPrice(Long brandId, Long productId, LocalDateTime date);
}
