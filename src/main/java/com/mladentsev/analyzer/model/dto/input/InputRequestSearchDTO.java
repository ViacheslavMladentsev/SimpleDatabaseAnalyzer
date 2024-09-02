package com.mladentsev.analyzer.model.dto.input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputRequestSearchDTO {

    List<InputCriteriaSearchDTO> criterias;

}
