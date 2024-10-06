package com.shop.pricing.infrastructure.api;

import com.shop.pricing.application.PriceService;
import com.shop.pricing.application.dto.PriceServiceRequest;
import com.shop.pricing.application.dto.PriceServiceResponse;
import com.shop.pricing.infrastructure.dto.PriceInfrastructureRequest;
import com.shop.pricing.infrastructure.dto.PriceInfrastructureResponse;
import com.shop.pricing.infrastructure.mapper.PriceMapper;
import com.shop.pricing.utils.DataTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PriceControllerTests {

    @Autowired
    private PriceController controller;

    @MockBean
    private PriceService service;

    @MockBean
    private PriceMapper mapper;

    private static final Long BRAND_ID = 1L;
    private static final Long PRODUCT_ID = 34555L;
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(LocalDate.of(2020, 7, 23), LocalTime.of(12, 0));

    @Test
    public void givenBrandIdProductIdDateShouldReturnOk() {
        PriceInfrastructureRequest infrastructureRequest = DataTests.generateInfrastructureRequest();
        PriceServiceRequest serviceRequest = DataTests.generateServiceRequest();
        PriceServiceResponse serviceResponse = DataTests.generateServiceResponse();
        PriceInfrastructureResponse infrastructureResponse = DataTests.generateInfrastructureResponse();
        when(mapper.toInfrastructureRequest(BRAND_ID, PRODUCT_ID, DATE_TIME)).thenReturn(infrastructureRequest);
        when(mapper.toServiceRequest(infrastructureRequest)).thenReturn(serviceRequest);
        when(service.searchPrice(serviceRequest)).thenReturn(serviceResponse);
        when(mapper.toInfrastructureResponse(serviceResponse)).thenReturn(infrastructureResponse);

        ResponseEntity<PriceInfrastructureResponse> response = controller.getPrice(BRAND_ID, PRODUCT_ID, String.valueOf(DATE_TIME));

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getProductId(), infrastructureResponse.getProductId());
        assertEquals(response.getBody().getBrandId(), infrastructureResponse.getBrandId());
        assertEquals(response.getBody().getPrice(), infrastructureResponse.getPrice());
        assertEquals(response.getBody().getPriceList(), infrastructureResponse.getPriceList());
        assertTrue(response.getBody().getStartDate().isBefore(DATE_TIME));
        assertTrue(response.getBody().getEndDate().isAfter(DATE_TIME));
    }
}
