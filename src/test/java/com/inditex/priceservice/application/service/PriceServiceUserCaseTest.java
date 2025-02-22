package com.inditex.priceservice.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.inditex.priceservice.application.dto.PriceResponse;
import com.inditex.priceservice.application.mapper.PriceMapper;
import com.inditex.priceservice.application.port.out.PriceRepositoryPort;
import com.inditex.priceservice.application.service.PriceServiceUserCase;
import com.inditex.priceservice.domain.Price;
import com.inditex.priceservice.domain.exception.PriceNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PriceServiceUserCaseTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceServiceUserCase priceService;

    @Test
    void testGetPrice_whenPriceFound() {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T10:00:00");

        Price price = Price.builder()
                .brandId(1)
                .productId(35455)
                .priceList(1)
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();

        PriceResponse expectedResponse = PriceResponse.builder()
                .brandId(1)
                .productId(35455)
                .priceList(1)
                .price(new BigDecimal("35.50"))
                .currency("EUR")
                .build();

        when(priceRepositoryPort.findPrice(eq(1), eq(35455), eq(applicationDate)))
                .thenReturn(Optional.of(price));
        when(priceMapper.toResponse(price)).thenReturn(expectedResponse);

        ResponseEntity<PriceResponse> response = priceService.getPrice(1, 35455, applicationDate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void testGetPrice_whenPriceNotFound() {
        LocalDateTime applicationDate = LocalDateTime.parse("2020-06-14T10:00:00");

        when(priceRepositoryPort.findPrice(eq(1), eq(35455), eq(applicationDate)))
                .thenReturn(Optional.empty());

        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () ->
                priceService.getPrice(1, 35455, applicationDate)
        );
        assertTrue(exception.getMessage().contains("not found"));
    }

}