package com.expensemanager.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "income_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Income {

    @Id
    @Column(name = "income_id")
    private String incomeId;

    @Column(name = "income_name")
    private String incomeName;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @Column(name = "date")
    private Date incomeReceivedDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "comment")
    private String comment;

    @Column(name = "month")
    private String incomeMonth;

    @Column(name = "year")
    private int incomeYear;
}
