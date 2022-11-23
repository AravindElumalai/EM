package com.expensemanager.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncomeRequestDto {

    private String incomeId;

    private String incomeName;

    private String subCategoryId;

    private BigDecimal amount;

    private String comment;

    private String month;

    private int year;

    private Date modifyDate;

    private Date startDate;

    private Date endDate;

}
