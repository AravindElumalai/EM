package com.expensemanager.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubCategoryResponseDTO {

    private String subCategoryId;

    private String subCategoryName;

    private CategoryResponseDTO categoryDetails;

    private boolean removeSubCategory;

    private Date subCategoryCreatedDate;
}
