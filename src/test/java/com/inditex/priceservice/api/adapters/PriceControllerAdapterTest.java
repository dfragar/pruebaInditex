    package com.inditex.priceservice.api.adapters;

    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
    import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

    import org.junit.jupiter.api.DisplayName;
    import org.junit.jupiter.api.Test;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.http.MediaType;
    import org.springframework.test.web.servlet.MockMvc;
    import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

    @SpringBootTest
    @AutoConfigureMockMvc
    class PriceControllerAdapterTest {

        @Autowired
        private MockMvc mockMvc;

        @Test
        @DisplayName("Test 1: 2020-06-14 10:00:00 -> Should return price from price list 1 (35.50 EUR)")
        void testPriceAt10OnJune14() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/apply-price")
                            .param("productId", "35455")
                            .param("brandId", "1")
                            .param("applicationDate", "2020-06-14T10:00:00")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.priceList").value(1))
                    .andExpect(jsonPath("$.price").value(35.50));
        }

        @Test
        @DisplayName("Test 2: 2020-06-14 16:00:00 -> Should return price from price list 2 (25.45 EUR)")
        void testPriceAt16OnJune14() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/apply-price")
                            .param("productId", "35455")
                            .param("brandId", "1")
                            .param("applicationDate", "2020-06-14T16:00:00")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.price").value(25.45));
        }

        @Test
        @DisplayName("Test 3: 2020-06-14 21:00:00 -> Should return price from price list 1 (35.50 EUR)")
        void testPriceAt21OnJune14() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/apply-price")
                            .param("productId", "35455")
                            .param("brandId", "1")
                            .param("applicationDate", "2020-06-14T21:00:00")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.priceList").value(1))
                    .andExpect(jsonPath("$.price").value(35.50));
        }

        @Test
        @DisplayName("Test 4: 2020-06-15 10:00:00 -> Should return price from price list 3 (30.50 EUR)")
        void testPriceAt10OnJune15() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/apply-price")
                            .param("productId", "35455")
                            .param("brandId", "1")
                            .param("applicationDate", "2020-06-15T10:00:00")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.priceList").value(3))
                    .andExpect(jsonPath("$.price").value(30.50));
        }

        @Test
        @DisplayName("Test 5: 2020-06-16 21:00:00 -> Should return price from price list 4 (38.95 EUR)")
        void testPriceAt21OnJune16() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/prices/apply-price")
                            .param("productId", "35455")
                            .param("brandId", "1")
                            .param("applicationDate", "2020-06-16T21:00:00")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.priceList").value(4))
                    .andExpect(jsonPath("$.price").value(38.95));
        }

    }