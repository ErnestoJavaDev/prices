package com.shop.pricing.domain.repository;

import com.shop.pricing.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findPrice(Long brandId, Long productId, LocalDateTime date);
}
