package com.shop.pricing.application;

import com.shop.pricing.application.dto.PriceServiceRequest;
import com.shop.pricing.application.dto.PriceServiceResponse;
import com.shop.pricing.domain.exception.PriceException;
import com.shop.pricing.domain.model.Price;
import com.shop.pricing.domain.repository.PriceRepository;
import com.shop.pricing.infrastructure.dto.PriceEntity;
import com.shop.pricing.infrastructure.mapper.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PriceService {

    private final PriceRepository repository;
    private final PriceMapper mapper;

    public PriceServiceResponse searchPrice(PriceServiceRequest serviceRequest) {

        List<Price> repositoryResponse =
                repository.findPrice(
                        serviceRequest.getBrandId(),
                        serviceRequest.getProductId(),
                        serviceRequest.getCurrentTime()
                );
        Price price = repositoryResponse.stream()
                .max(Comparator.comparingInt(Price::getPriority))
                .orElseThrow(() -> new PriceException("Price list is empty"));
        return mapper.toServiceResponse(price);
    }
}
