package com.expensemanager.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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

    private int month;

    private int year;

    private Date modifyDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

}
