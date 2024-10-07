package com.shop.pricing.infrastructure.persistence;

import com.shop.pricing.infrastructure.dto.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PricePersistenceRepository extends JpaRepository<PriceEntity, Long> {
    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.brandId = :brandId " +
            "AND p.productId = :productId " +
            "AND :currentDate BETWEEN p.startDate AND p.endDate " +
            "ORDER BY p.priority DESC")
    List<PriceEntity> findByBrandIdAndProductIdAndCurrentDate(
            Long brandId, Long productId, LocalDateTime currentDate);
}
