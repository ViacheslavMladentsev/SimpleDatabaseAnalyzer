package com.mladentsev.analyzer.application;


import com.mladentsev.analyzer.json.JsonFrom;
import com.mladentsev.analyzer.json.JsonTo;
import com.mladentsev.analyzer.model.dto.output.error.OutputErrorDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Data
@Component
public class Analyzer {

    private static final String SEARCH = "search";

    private static final String STATISTICS = "stat";


    private static String TYPE_OPERATION;

    private static String PATH_INPUT_FILE;

    private static String PATH_OUTPUT_FILE;

    private Analyzer() {
    }

    public static String getTypeOperation() {
        return TYPE_OPERATION;
    }

    public static String getPathInputFile() {
        return PATH_INPUT_FILE;
    }

    public static String getPathOutputFile() {
        return PATH_OUTPUT_FILE;
    }

    public static void run(String[] args) {
        TYPE_OPERATION = args[0];
        PATH_INPUT_FILE = args[1];
        PATH_OUTPUT_FILE = args[2];

//        System.out.println(customerService.findAllByLastName("Иванов").toString());
//        System.out.println(customerService.findAllByTitleAndCount("Продукт 6", 3));
//        System.out.println(customerService.findCustomersWithTotalPurchaseCostInRange(3000, 10000));
//        System.out.println(customerService.findCustomersWithLeastNumberOfPurchases(3));

//        CustomerService customerService = Application.getContext().getBean("customerService", CustomerService.class);

        Optional<Object> object = JsonFrom.readInputJson(SEARCH, PATH_INPUT_FILE, PATH_OUTPUT_FILE);
        if (!object.isPresent()) {
            JsonTo.recordOutputJson(new OutputErrorDTO("error input file", "некорректный input.json"),
                    PATH_OUTPUT_FILE);
        }

        if (SEARCH.equals(args[0])) {

        } else if (STATISTICS.equals(args[0])) {

        }


    }

}
