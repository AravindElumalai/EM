package com.expensemanager.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sub_category_table")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategory {

    @Id
    @Column(name = "sub_category_id")
    private String subCategoryId;

    @Column(name = "sub_category_name")
    private String subCategoryName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "remove_sub_category")
    private int removeSubCategory;

    @Column(name = "date")
    private Date subCreatedDate;

    @OneToMany(mappedBy = "subCategory")
    private List<Income> income;

    @OneToMany(mappedBy = "subCategory")
    private List<Expense> expense;
}
