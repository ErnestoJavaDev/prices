package com.shop.pricing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PriceIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String BRAND_ID = "1";
    private static final String PRODUCT_ID = "35455";

    @Test
    public void test1() throws Exception {
        mockMvc.perform(get("/shop/search/price")
                        .param("brandId", BRAND_ID)
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2020-06-14T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void test2() throws Exception {
        mockMvc.perform(get("/shop/search/price")
                        .param("brandId", BRAND_ID)
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2020-06-14T16:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(25.45));
    }

    @Test
    public void test3() throws Exception {
        mockMvc.perform(get("/shop/search/price")
                        .param("brandId", BRAND_ID)
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2020-06-14T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    public void test4() throws Exception {
        mockMvc.perform(get("/shop/search/price")
                        .param("brandId", BRAND_ID)
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2020-06-15T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30.50));
    }

    @Test
    public void test5() throws Exception {
        mockMvc.perform(get("/shop/search/price")
                        .param("brandId", BRAND_ID)
                        .param("productId", PRODUCT_ID)
                        .param("dateTime", "2020-06-16T21:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(38.95));
    }
}
