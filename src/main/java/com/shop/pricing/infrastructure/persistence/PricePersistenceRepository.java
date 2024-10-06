package com.shop.pricing.infrastructure.persistence;

import com.shop.pricing.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PricePersistenceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM Price p " +
            "WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND :currentDate BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    List<Price> findByBrandIdAndProductIdAndCurrentDate(
            Long brandId, Long productId, LocalDateTime currentDate);
}
