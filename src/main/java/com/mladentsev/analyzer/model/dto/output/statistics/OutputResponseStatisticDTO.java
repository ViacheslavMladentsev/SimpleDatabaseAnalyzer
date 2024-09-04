package com.mladentsev.analyzer.model.dto.output.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputResponseStatisticDTO {
    String type;
    Long toolDays;
    List<OutputCustomerDTO> customers;
    Double totalExpenses;
    Double avgExpenses;
}
