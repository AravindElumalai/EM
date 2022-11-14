package com.expensemanager.abstractlayer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expensemanager.customexception.SubCategoryException;
import com.expensemanager.dto.SubCategoryRequestDTO;
import com.expensemanager.dto.SubCategoryResponseDTO;

@Service
public interface SubCategoryAbstract {

    public SubCategoryResponseDTO createSubCategory(SubCategoryRequestDTO requestDto) throws SubCategoryException;

    public SubCategoryResponseDTO getSubCategory(String subCategoryId) throws SubCategoryException;

    public List<SubCategoryResponseDTO> getAllSubCategory() throws SubCategoryException;

    public List<SubCategoryResponseDTO> getAllSubCategoryForCategory(String categoryId) throws SubCategoryException;

    public SubCategoryResponseDTO updateSubCategory(SubCategoryRequestDTO requestDto) throws SubCategoryException;

    public void deleteSubCategory(String subCategoryId) throws SubCategoryException;

}
