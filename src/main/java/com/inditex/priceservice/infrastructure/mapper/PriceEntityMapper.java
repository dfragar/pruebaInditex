package com.inditex.priceservice.infrastructure.mapper;

import com.inditex.priceservice.domain.Price;
import com.inditex.priceservice.infrastructure.entity.PriceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    Price toDomain(PriceEntity entity);

    PriceEntity toEntity(Price domain);

}