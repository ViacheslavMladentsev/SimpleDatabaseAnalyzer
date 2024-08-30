package com.mladentsev.analyzer.service;

import com.mladentsev.analyzer.model.dto.output.search.OutputCustomerDTO;
import com.mladentsev.analyzer.repositories.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final ICustomerRepository iCustomerRepository;

    public List<OutputCustomerDTO> findAllByLastName(String lastName) {
        return iCustomerRepository.findAllByLastName(lastName)
                .stream()
                .map(entity -> new OutputCustomerDTO(entity.getName(), entity.getLastName()))
                .collect(Collectors.toList());
    }

    public List<OutputCustomerDTO> findAllByTitleAndCount(String title, int count) {
        return iCustomerRepository.findAllByTitleAndCount(title, count)
                .stream()
                .map(entity -> new OutputCustomerDTO(entity.getName(), entity.getLastName()))
                .collect(Collectors.toList());
    }

    public List<OutputCustomerDTO> findCustomersWithTotalPurchaseCostInRange(Integer min, Integer max) {
        return iCustomerRepository.findCustomersWithTotalPurchaseCostInRange(min, max)
                .stream()
                .map(entity -> new OutputCustomerDTO(entity.getName(), entity.getLastName()))
                .collect(Collectors.toList());
    }

    public List<OutputCustomerDTO> findCustomersWithLeastNumberOfPurchases(Integer countCustomers) {
        return iCustomerRepository.findCustomersWithLeastNumberOfPurchases(countCustomers)
                .stream()
                .map(entity -> new OutputCustomerDTO(entity.getName(), entity.getLastName()))
                .collect(Collectors.toList());
    }

}
