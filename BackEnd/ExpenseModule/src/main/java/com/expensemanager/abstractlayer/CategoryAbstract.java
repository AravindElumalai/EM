package com.expensemanager.abstractlayer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expensemanager.customexception.CategoryExpection;
import com.expensemanager.dto.CategoryRequestDTO;
import com.expensemanager.dto.CategoryResponseDTO;

@Service
public interface CategoryAbstract {
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequest) throws CategoryExpection;

    public CategoryResponseDTO getCategory(String categoryId) throws CategoryExpection;

    public List<CategoryResponseDTO> getAllCategory() throws CategoryExpection;

    public CategoryResponseDTO updateCategory(CategoryRequestDTO categoryRequest) throws CategoryExpection;

    public void deleteCategory(String categoryId) throws CategoryExpection;

    public CategoryResponseDTO blockCategory(CategoryRequestDTO categoryRequest) throws CategoryExpection;
}
