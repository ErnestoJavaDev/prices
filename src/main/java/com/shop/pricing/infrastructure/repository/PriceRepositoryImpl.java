package com.shop.pricing.infrastructure.repository;

import com.shop.pricing.domain.model.Price;
import com.shop.pricing.domain.repository.PriceRepository;
import com.shop.pricing.infrastructure.dto.PriceEntity;
import com.shop.pricing.infrastructure.persistence.PricePersistenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final PricePersistenceRepository persistenceRepository;

    @Override
    public List<PriceEntity> findPrice(Long brandId, Long productId, LocalDateTime date) {
        return persistenceRepository.findByBrandIdAndProductIdAndCurrentDate(brandId, productId, date);
    }
}
