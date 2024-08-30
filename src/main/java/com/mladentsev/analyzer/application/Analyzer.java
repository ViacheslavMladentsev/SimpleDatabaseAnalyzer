package com.mladentsev.analyzer.application;


import com.mladentsev.analyzer.service.CustomerService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class Analyzer {

    private Analyzer() {
    }

    private static String TYPE_OPERATION;
    private static String PATH_INPUT_FILE;
    private static String PATH_OUTPUT_FILE;

    public static String getTypeOperation() {
        return TYPE_OPERATION;
    }

    public static String getPathInputFile() {
        return PATH_INPUT_FILE;
    }

    public static String getPathOutputFile() {
        return PATH_OUTPUT_FILE;
    }

    public static void run(ConfigurableApplicationContext context, String[] args) {
        TYPE_OPERATION = args[0];
        PATH_INPUT_FILE = args[1];
        PATH_OUTPUT_FILE = args[2];
        CustomerService customerService = context.getBean("customerService", CustomerService.class);


//        System.out.println(customerService.findAllByLastName("Иванов").toString());
//        System.out.println(customerService.findAllByTitleAndCount("Продукт 6", 3));
//        System.out.println(customerService.findCustomersWithTotalPurchaseCostInRange(3000, 10000));
//        System.out.println(customerService.findCustomersWithLeastNumberOfPurchases(3));

        if ("search".equals(args[0])) {


//            JsonFrom.getRequestSearch()
//            InputRequestSearchDTO requestSearch = JsonFrom.getRequestSearch("/home/lieineyes/school21/SimpleDatabaseAnalyzer/src/main/resources/input.json");
//            System.out.println(requestSearch);
        } else if ("stat".equals(args[0])) {

        }


    }

}
