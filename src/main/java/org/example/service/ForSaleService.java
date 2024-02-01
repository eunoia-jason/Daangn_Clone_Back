package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.ForSale;
import org.example.dto.request.ForSaleRequest;
import org.example.repository.ForSaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ForSaleService {
    private final ForSaleRepository forSaleRepository;

    public void create( ForSaleRequest request ){
        ForSale forSale = ForSale.builder()
                .id(request.getId())
                .title(request.getTitle())
                .category(request.getCategory())
                .price(request.getPrice())
                .description(request.getDescription())
                .image(request.getImage())
                .build();
        forSaleRepository.save(forSale);
    }
}