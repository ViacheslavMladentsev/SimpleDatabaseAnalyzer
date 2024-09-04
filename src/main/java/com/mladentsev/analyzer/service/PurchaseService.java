package com.mladentsev.analyzer.service;

import com.mladentsev.analyzer.model.dto.output.search.OutputCustomerSearchDTO;
import com.mladentsev.analyzer.model.dto.output.statistics.OutputPurchaseDTO;
import com.mladentsev.analyzer.repositories.ICustomerRepository;
import com.mladentsev.analyzer.repositories.IPurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PurchaseService {
    private final IPurchaseRepository iPurchaseRepository;

    public List<Long> findDistinctIdByDateTimeBetween(Date startDate, Date endDate) {
        return iPurchaseRepository.findDistinctIdByDateTimeBetween(startDate, endDate);
    }

    public Double findTotalPriceAllPurchasesBetweenDate(Date startDate, Date endDate) {
        return iPurchaseRepository.findTotalPriceAllPurchasesBetweenDate(startDate, endDate);
    }



}
