package com.mladentsev.analyzer.service;

import com.mladentsev.analyzer.model.entity.CustomerEntity;
import com.mladentsev.analyzer.repositories.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final ICustomerRepository iCustomerRepository;

    public List<CustomerEntity> findAllByLastName(String lastName) {
        return iCustomerRepository.findAllByLastName(lastName);
    }

    public List<CustomerEntity> findAllByTitleAndCount(String title, int count) {
        return iCustomerRepository.findAllByTitleAndCount(title, count);
    }

}
