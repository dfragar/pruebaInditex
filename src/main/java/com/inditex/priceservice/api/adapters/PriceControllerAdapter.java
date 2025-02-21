package com.inditex.priceservice.api.adapters;

import com.inditex.priceservice.application.dto.ErrorResponse;
import com.inditex.priceservice.application.dto.PriceResponse;
import com.inditex.priceservice.application.port.in.PriceServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/prices", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class PriceControllerAdapter {

    private final PriceServicePort priceServicePort;
    
    @GetMapping("/apply-price")
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam @NotNull(message = "Product ID cannot be null") Integer productId,
            @RequestParam @NotNull(message = "Brand ID cannot be null") Integer brandId,
            @RequestParam @NotNull(message = "Application date cannot be null") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        return priceServicePort.getPrice(brandId, productId, applicationDate);
    }

}

