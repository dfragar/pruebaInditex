package com.inditex.priceservice.infrastructure.adapter;

import com.inditex.priceservice.application.port.out.PriceRepositoryPort;
import com.inditex.priceservice.domain.Price;
import com.inditex.priceservice.infrastructure.mapper.PriceEntityMapper;
import com.inditex.priceservice.infrastructure.repository.PriceJpaRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryPortAdapter implements PriceRepositoryPort {

    private final PriceJpaRepository jpaRepository;
    private final PriceEntityMapper mapper;

    @Override
    public Optional<Price> findPrice(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        return jpaRepository
                .findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                        brandId, productId, applicationDate, applicationDate)
                .map(mapper::toDomain);
    }

}