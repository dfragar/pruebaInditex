package com.inditex.priceservice.application.mapper;

import com.inditex.priceservice.application.dto.PriceResponse;
import com.inditex.priceservice.domain.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {
    PriceResponse toResponse(Price price);
}