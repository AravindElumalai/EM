package com.expensemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensemanager.domain.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, String> {

    public SubCategory findTopByOrderBySubCreatedDateDesc();

    public SubCategory findBySubCategoryId(String subCategoryId);

    public List<SubCategory> findByCategory_CategoryId(String categoryId);
}
