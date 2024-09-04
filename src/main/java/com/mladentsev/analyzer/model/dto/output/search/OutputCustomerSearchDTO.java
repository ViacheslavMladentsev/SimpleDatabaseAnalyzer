package com.mladentsev.analyzer.model.dto.output.search;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 ** Промежуточный класс, для формирования структуры объекта для записи в output.json
 */

@AllArgsConstructor
@Data
public class OutputCustomerSearchDTO {

    private String name;
    private String last_name;


}
