package com.mladentsev.analyzer.model.dto.output.error;

import lombok.AllArgsConstructor;

/**
 ** Класс, описывающий общую структуру объекта для вывода ошибки в выходной файл
 */

@AllArgsConstructor
public class OutputErrorDTO {

    String type;
    String message;

}
