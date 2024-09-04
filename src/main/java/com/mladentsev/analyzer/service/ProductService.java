package com.mladentsev.analyzer.service;

import com.mladentsev.analyzer.model.dto.output.statistics.OutputPurchaseDTO;
import com.mladentsev.analyzer.repositories.ICustomerRepository;
import com.mladentsev.analyzer.repositories.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final IProductRepository iProductRepository;

    public List<OutputPurchaseDTO> findAllProductAndPriceByCustomerIdBetweenDate(Long id, Date startDate, Date endDate) {
        return iProductRepository.findAllProductAndPriceByCustomerIdBetweenDate(id, startDate, endDate)
                .stream()
                .map(entity -> new OutputPurchaseDTO(entity.getName(), entity.getPrice()))
                .collect(Collectors.toList());
    }
}
