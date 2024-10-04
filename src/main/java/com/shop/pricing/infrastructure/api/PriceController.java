package com.shop.pricing.infrastructure.api;

import com.shop.pricing.application.PriceService;
import com.shop.pricing.infrastructure.dto.PriceInfrastructureRequest;
import com.shop.pricing.infrastructure.dto.PriceInfrastructureResponse;
import com.shop.pricing.infrastructure.mapper.PriceMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/shop")
@AllArgsConstructor
public class PriceController {

    private final PriceService service;
    private final PriceMapper mapper;

    @GetMapping("/search/price")
    public ResponseEntity<PriceInfrastructureResponse> getPrice(@RequestParam Long brandId, @RequestParam Long productId, @RequestParam String dateTime) {

        LocalDateTime currentTime = LocalDateTime.parse(dateTime);

        PriceInfrastructureRequest infrastructureRequest =
                mapper.toInfrastructureRequest(brandId, productId, currentTime);

        PriceInfrastructureResponse infrastructureResponse =
                mapper.toInfrastructureResponse(
                        service.searchPrice(
                                mapper.toServiceRequest(infrastructureRequest)
                        )
                );

        return ResponseEntity.ok(infrastructureResponse);
    }
}
