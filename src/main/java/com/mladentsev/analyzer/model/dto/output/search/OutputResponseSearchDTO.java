package com.mladentsev.analyzer.model.dto.output.search;

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
public class OutputResponseSearchDTO {
    String type;
    List<OutputSearchResultDTO> results;
}
