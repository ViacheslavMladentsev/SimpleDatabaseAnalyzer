package com.mladentsev.analyzer.model.dto.output.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 ** Промежуточный класс, для формирования структуры объекта для записи в output.json
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputPurchaseStatisticDTO {
    String name;
    Double price;
}
