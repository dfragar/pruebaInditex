package com.inditex.priceservice.application.port.in;

import com.inditex.priceservice.application.dto.PriceResponse;
import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;

public interface PriceServicePort {

    ResponseEntity<PriceResponse> getPrice(Integer brandId, Integer productId, LocalDateTime applicationDate);

}