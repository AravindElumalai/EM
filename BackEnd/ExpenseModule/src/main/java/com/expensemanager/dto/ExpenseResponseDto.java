package com.expensemanager.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpenseResponseDto {

    private String expenseId;

    private String expenseName;

    private String subCategoryId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;

    private BigDecimal amount;

    private String paymentMode;

    private String comment;

    private int month;

    private int year;
}
