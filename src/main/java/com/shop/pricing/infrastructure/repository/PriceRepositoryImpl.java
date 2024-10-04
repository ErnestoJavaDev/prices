package com.shop.pricing.infrastructure.repository;

import com.shop.pricing.domain.exception.PriceException;
import com.shop.pricing.domain.model.Price;
import com.shop.pricing.domain.repository.PriceRepository;
import com.shop.pricing.infrastructure.persistence.PricePersistenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Repository
@AllArgsConstructor
public class PriceRepositoryImpl implements PriceRepository {

    private final PricePersistenceRepository persistenceRepository;

    @Override
    public Price findPrice(Long brandId, Long productId, LocalDateTime date) {

        List<Price> prices = persistenceRepository.findByBrandIdAndProductIdAndCurrentDate(brandId, productId, date);
        Price response = prices.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .orElseThrow(() -> new PriceException("Price list no match with similar results"));
        return response;
    }
}
