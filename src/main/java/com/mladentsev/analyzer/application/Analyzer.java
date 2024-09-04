package com.mladentsev.analyzer.application;


import com.mladentsev.analyzer.json.AnalyzerJsonReadWrightFile;
import com.mladentsev.analyzer.model.dto.input.search.*;
import com.mladentsev.analyzer.model.dto.input.statystic.InputRequestStatisticDTO;
import com.mladentsev.analyzer.model.dto.output.error.OutputErrorDTO;
import com.mladentsev.analyzer.model.dto.output.search.*;
import com.mladentsev.analyzer.model.dto.output.statistics.*;
import com.mladentsev.analyzer.service.*;

import lombok.Data;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Data
@Component
public class Analyzer {

    private static final String SEARCH = "search";

    private static final String STATISTICS = "stat";

    private static final String BEAN_CUSTOMER_SERVICE = "customerService";

    private static final String BEAN_PURCHASE_SERVICE = "purchaseService";

    private static final String BEAN_PRODUCT_SERVICE = "productService";

    private static final String ERROR_INPUT_FILE = "error input file";

    private static final String MESSAGE_ERROR_INPUT_FILE = "некорректный inputStatistic.json";

    private static String TYPE_OPERATION;

    private static String PATH_INPUT_FILE;

    private static String PATH_OUTPUT_FILE;

    private static CustomerService customerService;

    private static PurchaseService purchaseService;

    private static ProductService productService;

    private Analyzer() {
    }

    public static void run(String[] args) {
        TYPE_OPERATION = args[0];
        PATH_INPUT_FILE = args[1];
        PATH_OUTPUT_FILE = args[2];

        customerService = Application.getContext().getBean(BEAN_CUSTOMER_SERVICE, CustomerService.class);
        purchaseService = Application.getContext().getBean(BEAN_PURCHASE_SERVICE, PurchaseService.class);
        productService = Application.getContext().getBean(BEAN_PRODUCT_SERVICE, ProductService.class);

        Optional<Object> object = AnalyzerJsonReadWrightFile.readInputJson(TYPE_OPERATION, PATH_INPUT_FILE, PATH_OUTPUT_FILE);
        if (!object.isPresent()) {
            AnalyzerJsonReadWrightFile.recordOutputJson(new OutputErrorDTO(ERROR_INPUT_FILE, MESSAGE_ERROR_INPUT_FILE),
                    PATH_OUTPUT_FILE);
        }

        if (SEARCH.equals(args[0])) {
            AnalyzerJsonReadWrightFile.recordOutputJson(getOutputResponseSearchDTO(object), PATH_OUTPUT_FILE);
        } else if (STATISTICS.equals(args[0])) {
            AnalyzerJsonReadWrightFile.recordOutputJson(getOutputResponseStatisticDTO(object), PATH_OUTPUT_FILE);
        }
    }

    private static OutputResponseSearchDTO getOutputResponseSearchDTO(Optional<Object> object) {
        InputRequestSearchDTO inputRequestSearchDTO = (InputRequestSearchDTO) object.get();
        List<OutputSearchResultDTO> outputSearchResultDTOList = new ArrayList<>();
        for (InputCriteriaSearchDTO criteria : inputRequestSearchDTO.getCriterias()) {
            OutputCriteriaSearchDTO outputCriteriaSearchDTO = new OutputCriteriaSearchDTO();
            List<OutputCustomerSearchDTO> outputCustomerSearchDTOList = null;
            switch (criteria.getCruteriaType()) {
                case LAST_NAME:
                    outputCustomerSearchDTOList = customerService.findAllByLastName(criteria.getLastName());
                    outputCriteriaSearchDTO.setLastName(criteria.getLastName());
                    break;
                case TITLE_AND_COUNT:
                    outputCustomerSearchDTOList = customerService.findAllByTitleAndCount(criteria.getProductName(), criteria.getMinTimes());
                    outputCriteriaSearchDTO.setProductName(criteria.getProductName());
                    outputCriteriaSearchDTO.setMinTimes(criteria.getMinTimes());
                    break;
                case RANGE_MIN_MAX:
                    outputCustomerSearchDTOList = customerService.findCustomersWithTotalPurchaseCostInRange(criteria.getMinExpenses(), criteria.getMaxExpenses());
                    outputCriteriaSearchDTO.setMinExpenses(criteria.getMinExpenses());
                    outputCriteriaSearchDTO.setMaxExpenses(criteria.getMaxExpenses());
                    break;
                case BAD_CUSTOMERS:
                    outputCustomerSearchDTOList = customerService.findCustomersWithLeastNumberOfPurchases(criteria.getBadCustomers());
                    outputCriteriaSearchDTO.setBadCustomers(criteria.getBadCustomers());
                    break;
                case INCORRECT_CRITERIA:
                    AnalyzerJsonReadWrightFile.recordOutputJson(getOutputResponseSearchDTO(object), PATH_OUTPUT_FILE);
                    break;
            }
            outputSearchResultDTOList.add(new OutputSearchResultDTO(outputCriteriaSearchDTO, outputCustomerSearchDTOList));

        }
        return new OutputResponseSearchDTO(SEARCH, outputSearchResultDTOList);
    }

    private static OutputResponseStatisticDTO getOutputResponseStatisticDTO(Optional<Object> object) {
        OutputResponseStatisticDTO outputResponseStatisticDTO = new OutputResponseStatisticDTO();
        outputResponseStatisticDTO.setType(TYPE_OPERATION);

        InputRequestStatisticDTO inputRequestStatisticDTO = (InputRequestStatisticDTO) object.get();
        Date startDate = inputRequestStatisticDTO.getStartDate();
        Date endDate = inputRequestStatisticDTO.getEndDate();

        long dateDiffInMillis = Math.abs(endDate.getTime() - startDate.getTime());
        outputResponseStatisticDTO.setToolDays(TimeUnit.DAYS.convert(dateDiffInMillis, TimeUnit.MILLISECONDS) + 1);

        List<Long> listIdCustomers = purchaseService.findDistinctIdByDateTimeBetween(startDate, endDate);
        List<OutputCustomerDTO> outputCustomerDTOList = new ArrayList<>();
        for (Long listIdCustomer : listIdCustomers) {
            String customerFullName = customerService.findFullNameById(listIdCustomer);

            List<OutputPurchaseDTO> outputPurchaseDTOList = productService.findAllProductAndPriceByCustomerIdBetweenDate(listIdCustomer, startDate, endDate);
            System.out.println(outputPurchaseDTOList);
            outputCustomerDTOList.add(new OutputCustomerDTO(customerFullName, outputPurchaseDTOList));
        }

        outputResponseStatisticDTO.setCustomers(outputCustomerDTOList);
        outputResponseStatisticDTO.setTotalExpenses(purchaseService.findTotalPriceAllPurchasesBetweenDate(startDate, endDate));
        outputResponseStatisticDTO.setAvgExpenses(outputResponseStatisticDTO.getTotalExpenses() / listIdCustomers.size());
        return outputResponseStatisticDTO;
    }

}
