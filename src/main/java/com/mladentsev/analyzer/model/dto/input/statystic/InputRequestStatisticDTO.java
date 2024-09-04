package com.mladentsev.analyzer.model.dto.input.statystic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputRequestStatisticDTO {

    private Date startDate;
    private Date endDate;

}
