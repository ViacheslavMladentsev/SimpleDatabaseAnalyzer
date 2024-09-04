package com.mladentsev.analyzer.model.dto.input.search;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputCriteriaSearchDTO {
    private String lastName;
    private String productName;
    private Integer minTimes;
    private Integer minExpenses;
    private Integer maxExpenses;
    private Integer badCustomers;


    public EnumCriteriasType getCruteriaType() {
        if (this.lastName != null &&
                this.productName == null && this.minTimes == null &&
                this.minExpenses == null && this.maxExpenses == null &&
                this.badCustomers == null) {
            return EnumCriteriasType.LAST_NAME;
        } else if (this.lastName == null &&
                this.productName != null && this.minTimes != null &&
                this.minExpenses == null && this.maxExpenses == null &&
                this.badCustomers == null) {
            return EnumCriteriasType.TITLE_AND_COUNT;
        } else if (this.lastName == null &&
                this.productName == null && this.minTimes == null &&
                this.minExpenses != null && this.maxExpenses != null &&
                this.badCustomers == null) {
            return EnumCriteriasType.RANGE_MIN_MAX;
        } else if (this.lastName == null &&
                this.productName == null && this.minTimes == null &&
                this.minExpenses == null && this.maxExpenses == null &&
                this.badCustomers != null) {
            return EnumCriteriasType.BAD_CUSTOMERS;
        } else {
            return EnumCriteriasType.INCORRECT_CRITERIA;
        }
    }
}
