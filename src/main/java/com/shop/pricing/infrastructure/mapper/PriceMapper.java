package com.shop.pricing.infrastructure.mapper;

import com.shop.pricing.application.dto.PriceServiceRequest;
import com.shop.pricing.application.dto.PriceServiceResponse;
import com.shop.pricing.domain.model.Price;
import com.shop.pricing.infrastructure.dto.PriceInfrastructureRequest;
import com.shop.pricing.infrastructure.dto.PriceInfrastructureResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    PriceInfrastructureRequest toInfrastructureRequest(Long brandId, Long productId, LocalDateTime currentTime);
    PriceServiceRequest toServiceRequest(PriceInfrastructureRequest infrastructureRequest);
    PriceInfrastructureResponse toInfrastructureResponse(PriceServiceResponse serviceResponse);
    PriceServiceResponse toServiceResponse(Price repositoryResponse);
}
