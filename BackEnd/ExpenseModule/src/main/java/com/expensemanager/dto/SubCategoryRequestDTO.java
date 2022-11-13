package com.expensemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryRequestDTO {

    private String subCategoryId;

    private String subCategoryName;

    private String categoryId;

    private boolean removeSubCategory;
}
