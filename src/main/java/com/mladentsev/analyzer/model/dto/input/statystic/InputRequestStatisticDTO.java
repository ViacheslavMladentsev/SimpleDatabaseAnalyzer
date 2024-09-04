package com.mladentsev.analyzer.model.dto.input.statystic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 ** Класс, описывающий общую структуру входного файла для операции stat в виде java объекта
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputRequestStatisticDTO {

    private Date startDate;
    private Date endDate;

}
