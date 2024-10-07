package com.shop.pricing.infrastructure.repository;

import com.shop.pricing.domain.model.Price;
import com.shop.pricing.domain.repository.PriceRepository;
import com.shop.pricing.infrastructure.dto.PriceEntity;
import com.shop.pricing.infrastructure.mapper.PriceMapper;
import com.shop.pricing.infrastructure.persistence.PricePersistenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final PricePersistenceRepository persistenceRepository;
    private final PriceMapper mapper;

    @Override
    public List<Price> findPrice(Long brandId, Long productId, LocalDateTime date) {
        List<PriceEntity> prices = persistenceRepository.findByBrandIdAndProductIdAndCurrentDate(brandId, productId, date);
        return prices.stream()
                .map(mapper::toPrice)
                .collect(Collectors.toList());
    }
}
