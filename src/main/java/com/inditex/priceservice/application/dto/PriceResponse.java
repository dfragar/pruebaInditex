package com.inditex.priceservice.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceResponse {

    private Integer productId;

    private Integer brandId;

    private Integer priceList;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BigDecimal price;

    private String currency;

}