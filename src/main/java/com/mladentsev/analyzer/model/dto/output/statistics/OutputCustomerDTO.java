package com.mladentsev.analyzer.model.dto.output.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputCustomerDTO {
    String name;
    List<OutputPurchaseDTO> purchases;
}
