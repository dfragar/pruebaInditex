package com.inditex.priceservice.e2e;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.inditex.priceservice.application.dto.PriceResponse;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String buildUrl(String productId, String brandId, String applicationDate) {
        return UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(port)
                .path("/api/prices/apply-price")
                .queryParam("productId", productId)
                .queryParam("brandId", brandId)
                .queryParam("applicationDate", applicationDate)
                .build()
                .toUriString();
    }

    @Test
    void testPriceAt10OnJune14() {
        String url = buildUrl("35455", "1", "2020-06-14T10:00:00");
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(url, PriceResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        PriceResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getPriceList());
        assertEquals(new BigDecimal("35.50"), body.getPrice());
    }

    @Test
    void testPriceAt16OnJune14() {
        String url = buildUrl("35455", "1", "2020-06-14T16:00:00");
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(url, PriceResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        PriceResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(2, body.getPriceList());
        assertEquals(new BigDecimal("25.45"), body.getPrice());
    }

    @Test
    void testPriceAt21OnJune14() {
        String url = buildUrl("35455", "1", "2020-06-14T21:00:00");
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(url, PriceResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        PriceResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(1, body.getPriceList());
        assertEquals(new BigDecimal("35.50"), body.getPrice());
    }

    @Test
    void testPriceAt10OnJune15() {
        String url = buildUrl("35455", "1", "2020-06-15T10:00:00");
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(url, PriceResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        PriceResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(3, body.getPriceList());
        assertEquals(new BigDecimal("30.50"), body.getPrice());
    }

    @Test
    void testPriceAt21OnJune16() {
        String url = buildUrl("35455", "1", "2020-06-16T21:00:00");
        ResponseEntity<PriceResponse> response = restTemplate.getForEntity(url, PriceResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        PriceResponse body = response.getBody();
        assertNotNull(body);
        assertEquals(4, body.getPriceList());
        assertEquals(new BigDecimal("38.95"), body.getPrice());
    }

}