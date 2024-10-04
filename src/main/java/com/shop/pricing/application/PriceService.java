package com.shop.pricing.application;

import com.shop.pricing.application.dto.PriceServiceRequest;
import com.shop.pricing.application.dto.PriceServiceResponse;
import com.shop.pricing.domain.model.Price;
import com.shop.pricing.domain.repository.PriceRepository;
import com.shop.pricing.infrastructure.mapper.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PriceService {

    private final PriceRepository repository;
    private final PriceMapper mapper;

    public PriceServiceResponse searchPrice(PriceServiceRequest serviceRequest) {

        Price repositoryResponse =
                repository.findPrice(
                        serviceRequest.getBrandId(),
                        serviceRequest.getProductId(),
                        serviceRequest.getCurrentTime()
                );
        return mapper.toServiceResponse(repositoryResponse);
    }
}
