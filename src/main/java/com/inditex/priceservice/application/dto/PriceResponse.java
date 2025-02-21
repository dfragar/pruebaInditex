package com.inditex.priceservice.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
        name = "PriceResponse",
        description = "Schema to hold successful price response information"
)
public class PriceResponse {

    @Schema(description = "Product identifier for which the price is applicable", example = "35455")
    private Integer productId;

    @Schema(description = "Brand identifier for which the price is applicable", example = "1")
    private Integer brandId;

    @Schema(description = "Price list identifier associated with the price", example = "2")
    private Integer priceList;

    @Schema(description = "Start date for the price validity", example = "2023-05-15T10:00:00")
    private LocalDateTime startDate;

    @Schema(description = "End date for the price validity", example = "2023-06-15T10:00:00")
    private LocalDateTime endDate;

    @Schema(description = "Final price for the product", example = "25.45")
    private BigDecimal price;

    @Schema(description = "Currency code of the price", example = "EUR")
    private String currency;

}