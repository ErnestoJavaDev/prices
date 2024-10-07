package com.shop.pricing.application;

import com.shop.pricing.application.dto.PriceServiceRequest;
import com.shop.pricing.application.dto.PriceServiceResponse;
import com.shop.pricing.domain.exception.PriceException;
import com.shop.pricing.domain.model.Price;
import com.shop.pricing.domain.repository.PriceRepository;
import com.shop.pricing.infrastructure.dto.PriceEntity;
import com.shop.pricing.infrastructure.mapper.PriceMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.shop.pricing.utils.DataTests.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PriceServiceTests {

    @Autowired
    private PriceService service;

    @MockBean
    private PriceRepository repository;

    @MockBean
    private PriceMapper mapper;

    @Test
    public void givenServiceRequestShouldReturnValidResponse() {
        PriceServiceRequest serviceRequest = generateServiceRequest();
        Price price = generatePrice();
        List<Price> prices = Arrays.asList(price);
        PriceServiceResponse serviceResponse = generateServiceResponse();

        when(repository.findPrice(serviceRequest.getBrandId(), serviceRequest.getProductId(), serviceRequest.getCurrentTime()))
                .thenReturn(prices);
        when(mapper.toServiceResponse(price)).thenReturn(serviceResponse);

        PriceServiceResponse response = service.searchPrice(serviceRequest);

        assertEquals(response.getBrandId(), serviceResponse.getBrandId());
        assertEquals(response.getProductId(), serviceResponse.getProductId());
        assertEquals(response.getPrice(), serviceResponse.getPrice());
        assertEquals(response.getPriceList(), serviceResponse.getPriceList());
        assertEquals(response.getStartDate(), serviceResponse.getStartDate());
        assertEquals(response.getEndDate(), serviceResponse.getEndDate());
    }

    @Test
    public void givenServiceRequestMultipleChoiceShouldReturnValidResponse() {
        PriceServiceRequest serviceRequest = generateServiceRequest();
        Price price = generatePrice();
        price.setPriority(0);
        Price price2 = generatePrice();
        price2.setPrice(66.65);
        List<Price> prices = Arrays.asList(price, price2);
        PriceServiceResponse serviceResponse = generateServiceResponse();
        serviceResponse.setPrice(66.65);

        when(repository.findPrice(serviceRequest.getBrandId(), serviceRequest.getProductId(), serviceRequest.getCurrentTime()))
                .thenReturn(prices);
        when(mapper.toServiceResponse(price2)).thenReturn(serviceResponse);

        PriceServiceResponse response = service.searchPrice(serviceRequest);

        assertEquals(response.getBrandId(), price2.getBrandId());
        assertEquals(response.getProductId(), price2.getProductId());
        assertEquals(response.getPrice(), price2.getPrice());
        assertEquals(response.getPriceList(), price2.getPriceList());
        assertEquals(response.getStartDate(), price2.getStartDate());
        assertEquals(response.getEndDate(), price2.getEndDate());
    }


    @Test
    public void givenServiceRequestShouldReturnException() {
        PriceServiceRequest serviceRequest = generateServiceRequest();
        List<Price> prices = new ArrayList<>();

        when(repository.findPrice(serviceRequest.getBrandId(), serviceRequest.getProductId(), serviceRequest.getCurrentTime()))
                .thenReturn(prices);

        assertThrows(PriceException.class, () -> {
            service.searchPrice(serviceRequest);
        });
    }
}
