package com.expensemanager.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensemanager.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String>{
    
    public Category findTopByOrderByCategoryIdDesc();

    public Category findTopByOrderByCreateDateDesc();

    public Category findByCategoryId(String categoryId);
}
