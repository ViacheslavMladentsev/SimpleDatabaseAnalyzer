package com.mladentsev.analyzer.application;


import com.mladentsev.analyzer.json.AnalyzerJsonReadWrightFile;
import com.mladentsev.analyzer.model.dto.input.InputCriteriaSearchDTO;
import com.mladentsev.analyzer.model.dto.input.InputRequestSearchDTO;
import com.mladentsev.analyzer.model.dto.output.error.OutputErrorDTO;
import com.mladentsev.analyzer.model.dto.output.search.OutputCriteriaSearchDTO;
import com.mladentsev.analyzer.model.dto.output.search.OutputCustomerSearchDTO;
import com.mladentsev.analyzer.model.dto.output.search.OutputResponseSearchDTO;
import com.mladentsev.analyzer.model.dto.output.search.OutputSearchResultDTO;
import com.mladentsev.analyzer.service.CustomerService;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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

        Optional<Object> object = AnalyzerJsonReadWrightFile.readInputJson(SEARCH, PATH_INPUT_FILE, PATH_OUTPUT_FILE);
        if (!object.isPresent()) {
            AnalyzerJsonReadWrightFile.recordOutputJson(new OutputErrorDTO("error input file", "некорректный input.json"),
                    PATH_OUTPUT_FILE);
        }

        if (SEARCH.equals(args[0])) {
            AnalyzerJsonReadWrightFile.recordOutputJson(getOutputResponseSearchDTO(object), PATH_OUTPUT_FILE);
        } else if (STATISTICS.equals(args[0])) {

        }


    }

    private static OutputResponseSearchDTO getOutputResponseSearchDTO(Optional<Object> object) {
        InputRequestSearchDTO inputRequestSearchDTO = (InputRequestSearchDTO) object.get();
        CustomerService customerService = Application.getContext().getBean("customerService", CustomerService.class);
        List<OutputSearchResultDTO> outputSearchResultDTOList = new ArrayList<>();
        for (InputCriteriaSearchDTO criteria : inputRequestSearchDTO.getCriterias()) {
            // todo валидация criteria
            OutputCriteriaSearchDTO outputCriteriaSearchDTO = new OutputCriteriaSearchDTO();
            List<OutputCustomerSearchDTO> outputCustomerSearchDTOList = null;
            if (criteria.getLastName() != null) {
                outputCustomerSearchDTOList = customerService.findAllByLastName(criteria.getLastName());
                outputCriteriaSearchDTO.setLastName(criteria.getLastName());
            } else if (criteria.getProductName() != null && criteria.getMinTimes() != null) {
                outputCustomerSearchDTOList = customerService.findAllByTitleAndCount(criteria.getProductName(), criteria.getMinTimes());
                outputCriteriaSearchDTO.setProductName(criteria.getProductName());
                outputCriteriaSearchDTO.setMinTimes(criteria.getMinTimes());
            } else if (criteria.getMinExpenses() != null && criteria.getMaxExpenses() != null) {
                outputCustomerSearchDTOList = customerService.findCustomersWithTotalPurchaseCostInRange(criteria.getMinExpenses(), criteria.getMaxExpenses());
                outputCriteriaSearchDTO.setMinExpenses(criteria.getMinExpenses());
                outputCriteriaSearchDTO.setMaxExpenses(criteria.getMaxExpenses());
            } else if (criteria.getBadCustomers() != null) {
                outputCustomerSearchDTOList = customerService.findCustomersWithLeastNumberOfPurchases(criteria.getBadCustomers());
                outputCriteriaSearchDTO.setBadCustomers(criteria.getBadCustomers());
            }
            outputSearchResultDTOList.add(new OutputSearchResultDTO(outputCriteriaSearchDTO, outputCustomerSearchDTOList));
        }
        return new OutputResponseSearchDTO(SEARCH, outputSearchResultDTOList);
    }



}
