package com.mladentsev.analyzer.model.dto.output.search;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 ** Промежуточный класс, для формирования структуры объекта для записи в output.json
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputCriteriaSearchDTO {

    private String lastName;
    private String productName;
    private Integer minTimes;
    private Integer minExpenses;
    private Integer maxExpenses;
    private Integer badCustomers;

}
