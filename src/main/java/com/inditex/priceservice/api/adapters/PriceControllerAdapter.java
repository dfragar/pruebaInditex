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
@Tag(
        name = "Price API",
        description = "API to fetch the applicable price for a product based on brand and application date"
)
public class PriceControllerAdapter {

    private final PriceServicePort priceServicePort;

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK - Price successfully retrieved",
                    content = @Content(
                            schema = @Schema(implementation = PriceResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request - Invalid input parameters",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP Status Not Found - Price not found for the given parameters (e.g., invalid product or brand)",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error - Unexpected error occurred",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    @Operation(
            summary = "Retrieve applicable price for a product",
            description = "Fetches the applicable price for a given product and brand based on the provided application date."
    )
    @GetMapping("/apply-price")
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam @NotNull(message = "Product ID cannot be null") Integer productId,
            @RequestParam @NotNull(message = "Brand ID cannot be null") Integer brandId,
            @RequestParam @NotNull(message = "Application date cannot be null") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        return priceServicePort.getPrice(brandId, productId, applicationDate);
    }

}

