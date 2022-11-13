package com.expensemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensemanager.domain.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, String> {

}
