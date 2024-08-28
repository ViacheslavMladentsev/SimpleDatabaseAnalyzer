package com.mladentsev.analyzer.application;

import com.mladentsev.analyzer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Analyzer {

    public static void run(ConfigurableApplicationContext context) {
        CustomerService customerService = context.getBean("customerService", CustomerService.class);
        System.out.println(customerService.findAllByLastName("Иванов"));

        System.out.println(customerService.findAllByTitleAndCount("Продукт 6", 3));
    }

}
