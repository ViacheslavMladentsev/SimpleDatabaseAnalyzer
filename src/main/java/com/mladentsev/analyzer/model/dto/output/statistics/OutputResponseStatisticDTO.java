package com.mladentsev.analyzer.model.dto.output.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 ** Класс, описывающий конечный объект для записи в output.json
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputResponseStatisticDTO {
    String type;
    Long toolDays;
    List<OutputCustomerStatisticDTO> customers;
    Double totalExpenses;
    Double avgExpenses;
}
