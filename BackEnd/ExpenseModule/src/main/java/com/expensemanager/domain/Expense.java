package com.expensemanager.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "expense_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {

    @Id
    @Column(name = "expense_id")
    private String expenseId;

    @Column(name = "expense_name")
    private String expenseName;

    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expenseReceivedDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_mode")
    private String paymentMode;

    @Column(name = "comment")
    private String expenseComment;

    @Column(name = "month")
    private int expenseMonth;

    @Column(name = "year")
    private int expenseYear;

}
