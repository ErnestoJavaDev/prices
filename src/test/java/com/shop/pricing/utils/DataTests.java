package com.shop.pricing.utils;

import com.shop.pricing.application.dto.PriceServiceRequest;
import com.shop.pricing.application.dto.PriceServiceResponse;
import com.shop.pricing.domain.model.Price;
import com.shop.pricing.infrastructure.dto.PriceInfrastructureRequest;
import com.shop.pricing.infrastructure.dto.PriceInfrastructureResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DataTests {

    private static final Long ID = 1L;
    private static final Long BRAND_ID = 1L;
    private static final Long PRODUCT_ID = 34555L;
    private static final LocalDateTime DATE_TIME = LocalDateTime.of(LocalDate.of(2020, 7, 23), LocalTime.of(12, 0));
    private static final Integer PRICE_LIST = 3;
    private static final Double PRICE = 34.65;
    private static final LocalDateTime START_DATE = LocalDateTime.of(LocalDate.of(2020, 6, 23),LocalTime.of(12, 0));
    private static final LocalDateTime END_DATE = LocalDateTime.of(LocalDate.of(2020, 10, 23), LocalTime.of(12, 0));;
    private static final Integer PRIORITY = 1;
    private static final String CURRENCY = "€";

    public static PriceInfrastructureRequest generateInfrastructureRequest() {
        return new PriceInfrastructureRequest(BRAND_ID, PRODUCT_ID, DATE_TIME);
    }

    public static PriceServiceRequest generateServiceRequest() {
        return new PriceServiceRequest(BRAND_ID, PRODUCT_ID, DATE_TIME);
    }

    public static PriceServiceResponse generateServiceResponse() {
        return new PriceServiceResponse(BRAND_ID, PRODUCT_ID, PRICE_LIST, PRICE, START_DATE, END_DATE);
    }

    public static PriceInfrastructureResponse generateInfrastructureResponse() {
        return new PriceInfrastructureResponse(BRAND_ID, PRODUCT_ID, PRICE_LIST, PRICE, START_DATE, END_DATE);
    }

    public static Price generatePrice() {
        return new Price(ID, BRAND_ID, PRODUCT_ID, PRICE_LIST, PRIORITY, PRICE, CURRENCY, START_DATE, END_DATE);
    }
}
