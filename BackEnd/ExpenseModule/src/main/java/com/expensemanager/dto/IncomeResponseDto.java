package com.expensemanager.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncomeResponseDto {

    private String incomeId;

    private String incomeName;

    private SubCategoryResponseDTO subcategory;

    private Date createdDate;

    private BigDecimal amount;

    private String comment;

    private int month;

    private int year;
}
