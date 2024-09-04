package com.mladentsev.analyzer.model.dto.input.search;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс, описывающий список критериев из входного файла для операции search.
 */
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

    /**
     * Метод, производящий частичную валидацию входных критериев из входного файла
     * и определяющий конкретный тип критерия
     * @return возвращает тип критерия
     */
    public EnumCriteriaType getCriteriaType() {
        if (this.lastName != null &&
                this.productName == null && this.minTimes == null &&
                this.minExpenses == null && this.maxExpenses == null &&
                this.badCustomers == null) {
            return EnumCriteriaType.LAST_NAME;
        } else if (this.lastName == null &&
                this.productName != null && this.minTimes != null &&
                this.minExpenses == null && this.maxExpenses == null &&
                this.badCustomers == null) {
            return EnumCriteriaType.TITLE_AND_COUNT;
        } else if (this.lastName == null &&
                this.productName == null && this.minTimes == null &&
                this.minExpenses != null && this.maxExpenses != null &&
                this.badCustomers == null) {
            return EnumCriteriaType.RANGE_MIN_MAX;
        } else if (this.lastName == null &&
                this.productName == null && this.minTimes == null &&
                this.minExpenses == null && this.maxExpenses == null &&
                this.badCustomers != null) {
            return EnumCriteriaType.BAD_CUSTOMERS;
        } else {
            return EnumCriteriaType.INCORRECT_CRITERIA;
        }
    }
}
