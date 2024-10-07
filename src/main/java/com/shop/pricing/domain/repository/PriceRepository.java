package com.shop.pricing.domain.repository;

import com.shop.pricing.infrastructure.dto.PriceEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<PriceEntity> findPrice(Long brandId, Long productId, LocalDateTime date);
}
