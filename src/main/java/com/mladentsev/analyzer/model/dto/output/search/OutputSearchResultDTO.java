package com.mladentsev.analyzer.model.dto.output.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 ** Промежуточный класс, для формирования структуры объекта для записи в output.json
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputSearchResultDTO {
    OutputCriteriaSearchDTO criteria;
    List<OutputCustomerSearchDTO> results;
}
