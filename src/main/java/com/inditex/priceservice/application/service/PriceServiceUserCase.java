package com.inditex.priceservice.application.service;

import com.inditex.priceservice.application.dto.PriceResponse;
import com.inditex.priceservice.application.mapper.PriceMapper;
import com.inditex.priceservice.application.port.in.PriceServicePort;
import com.inditex.priceservice.application.port.out.PriceRepositoryPort;
import com.inditex.priceservice.domain.Price;
import com.inditex.priceservice.domain.exception.PriceNotFoundException;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PriceServiceUserCase implements PriceServicePort {

    private final PriceRepositoryPort priceRepositoryPort;
    private final PriceMapper priceMapper;

    @Override
    public ResponseEntity<PriceResponse> getPrice(Integer brandId, Integer productId,
            LocalDateTime applicationDate) {
        Price price = priceRepositoryPort.findPrice(brandId, productId, applicationDate)
                .orElseThrow(() -> new PriceNotFoundException(
                        "Price",
                        "brandId, productId, and applicationDate",
                        String.format("brandId: %d, productId: %d, applicationDate: %s", brandId, productId,
                                applicationDate)
                ));

        PriceResponse priceResponse = priceMapper.toResponse(price);
        return ResponseEntity.ok(priceResponse);
    }

}