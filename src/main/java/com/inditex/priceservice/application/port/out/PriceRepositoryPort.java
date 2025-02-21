package com.inditex.priceservice.application.port.out;

import com.inditex.priceservice.domain.Price;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {

    Optional<Price> findPrice(Integer brandId, Integer productId, LocalDateTime applicationDate);

}