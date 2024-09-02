package com.mladentsev.analyzer.model.dto.output.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputSearchResultDTO {
    OutputCriteriaSearchDTO criteria;
    List<OutputCustomerSearchDTO> results;
}
