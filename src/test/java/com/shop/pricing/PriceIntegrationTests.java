package com.shop.pricing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PriceIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String URL = "/shop/search/prices";
    private static final String BRAND_ID = "1";
    private static final String PRODUCT_ID = "35455";

    @Test
    public void testEarlyThan15ReturnPriceList1() throws Exception {
        mockMvc.perform(get(URL)
                        .param("brandId", BRAND_ID)
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void testReturnHigherPriority() throws Exception {
        mockMvc.perform(get(URL)
                        .param("brandId", BRAND_ID)
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2020-06-14T16:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    public void testLateThan1830ReturnPriceList1() throws Exception {
        mockMvc.perform(get(URL)
                        .param("brandId", BRAND_ID)
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2020-06-14T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void testReturnPriceList3() throws Exception {
        mockMvc.perform(get(URL)
                        .param("brandId", BRAND_ID)
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2020-06-15T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    public void testReturnPriceList4() throws Exception {
        mockMvc.perform(get(URL)
                        .param("brandId", BRAND_ID)
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2020-06-16T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95));
    }

    @Test
    public void testInvalidParamReturnMethodArgumentTypeMismatchException() throws Exception {
        mockMvc.perform(get(URL)
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2024-10-01T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Param 'brandId' is mandatory"))
                .andExpect(jsonPath("$.errorCode").value("400"));
    }

    @Test
    public void testInvalidValueReturnMethodArgumentTypeMismatchException() throws Exception {
        mockMvc.perform(get(URL)
                        .param("brandId", "invalid")
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2024-10-01T10:00:00")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid value for param: 'brandId'"))
                .andExpect(jsonPath("$.errorCode").value("400"));
    }

    @Test
    public void testIncorrectParamReturnDateTimeParseException() throws Exception {
        mockMvc.perform(get(URL)
                        .param("brandId", BRAND_ID)
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2024")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Cant parse dateTime as date"))
                .andExpect(jsonPath("$.errorCode").value("400"));
    }
}
