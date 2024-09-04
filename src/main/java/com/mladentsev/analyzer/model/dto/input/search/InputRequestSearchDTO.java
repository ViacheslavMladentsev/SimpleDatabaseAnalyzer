package com.mladentsev.analyzer.model.dto.input.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Класс, описывающий общую структуру входного файла для операции search в виде java объекта
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputRequestSearchDTO {

    List<InputCriteriaSearchDTO> criterias;

}
