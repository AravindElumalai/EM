package com.expensemanager.businesslogic;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.expensemanager.Constant.SubCategoryConstant;
import com.expensemanager.abstractlayer.SubCategoryAbstract;
import com.expensemanager.customexception.SubCategoryException;
import com.expensemanager.domain.SubCategory;
import com.expensemanager.dto.CategoryResponseDTO;
import com.expensemanager.dto.SubCategoryRequestDTO;
import com.expensemanager.dto.SubCategoryResponseDTO;
import com.expensemanager.repository.CategoryRepository;
import com.expensemanager.repository.SubCategoryRepository;

public class SubCategoryLogic implements SubCategoryAbstract {

    private Logger logger = LoggerFactory.getLogger(SubCategoryLogic.class);

    @Autowired
    private SubCategoryRepository subCategoryRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public SubCategoryResponseDTO createSubCategory(SubCategoryRequestDTO requestDto) throws SubCategoryException {
        logger.info(" ::: SubCategoryLogic createSubCategory starts ::: ");
        try {
            SubCategory saveObject = SubCategory.builder().subCategoryId(generateSubCategoryId())
                    .subCategoryName(requestDto.getSubCategoryName())
                    .category(categoryRepo.findByCategoryId(requestDto.getCategoryId()))
                    .removeSubCategory(requestDto.isRemoveSubCategory() ? 1 : 0).subCreatedDate(new Date()).build();
            saveObject = subCategoryRepo.saveAndFlush(saveObject);
            return SubCategoryResponseDTO.builder()
                    .subCategoryId(saveObject.getSubCategoryId()).subCategoryName(saveObject.getSubCategoryName())
                    .categoryDetails(CategoryResponseDTO.builder().categoryId(saveObject.getCategory().getCategoryId())
                            .categoryName(saveObject.getCategory().getCategoryName()).build())
                    .removeSubCategory(saveObject.getRemoveSubCategory() == 1 ? true : false)
                    .subCategoryCreatedDate(saveObject.getSubCreatedDate()).build();
        } catch (Exception e) {
            throw new SubCategoryException("Exception Occured during saving object ::" + e.getMessage());
        }

    }

    @Override
    public SubCategoryResponseDTO getSubCategory(String subCategoryId) throws SubCategoryException {
        logger.info(" ::: SubCategoryLogic getSubCategory starts ::: ");
        try {
            SubCategory getCategory = subCategoryRepo.findBySubCategoryId(subCategoryId);
            if (Objects.nonNull(getCategory)) {
                return SubCategoryResponseDTO.builder()
                        .subCategoryId(getCategory.getSubCategoryId()).subCategoryName(getCategory.getSubCategoryName())
                        .categoryDetails(
                                CategoryResponseDTO.builder().categoryId(getCategory.getCategory().getCategoryId())
                                        .categoryName(getCategory.getCategory().getCategoryName()).build())
                        .removeSubCategory(getCategory.getRemoveSubCategory() == 1 ? true : false)
                        .subCategoryCreatedDate(getCategory.getSubCreatedDate()).build();
            } else {
                throw new SubCategoryException(String.format("Sub-Category %s is not found", subCategoryId));
            }

        } catch (Exception e) {
            throw new SubCategoryException("Exception Occured during retireving object ::" + e.getMessage());
        }

    }

    @Override
    public List<SubCategoryResponseDTO> getAllSubCategory() throws SubCategoryException {
        logger.info(" ::: SubCategoryLogic getAllSubCategory starts ::: ");
        try {
            List<SubCategory> getAllSubCategories = subCategoryRepo.findAll();
            if (getAllSubCategories.isEmpty()) {
                throw new SubCategoryException("No SubCategory found ");
            } else {
                return getAllSubCategories
                        .stream().map(
                                alSubCat -> SubCategoryResponseDTO.builder().subCategoryId(alSubCat.getSubCategoryId())
                                        .subCategoryName(alSubCat.getSubCategoryName())
                                        .categoryDetails(CategoryResponseDTO.builder()
                                                .categoryId(alSubCat.getCategory().getCategoryId())
                                                .categoryName(alSubCat.getCategory().getCategoryName()).build())
                                        .removeSubCategory(alSubCat.getRemoveSubCategory() == 1 ? true : false)
                                        .subCategoryCreatedDate(alSubCat.getSubCreatedDate()).build())
                        .collect(Collectors.toList());
            }

        } catch (Exception e) {
            throw new SubCategoryException(
                    String.format(" Exception occured in get all subcategory %s", e.getMessage()));
        }

    }

    @Override
    public List<SubCategoryResponseDTO> getAllSubCategoryForCategory(String categoryId) throws SubCategoryException {
        logger.info(" ::: SubCategoryLogic getAllSubCategoryForCategory starts ::: ");
        try {
            List<SubCategory> allSubCatList = subCategoryRepo.findByCategory_CategoryId(categoryId);
            return allSubCatList.stream()
                    .map(subCatList -> SubCategoryResponseDTO.builder().subCategoryId(subCatList.getSubCategoryId())
                            .subCategoryName(subCatList.getSubCategoryName())
                            .categoryDetails(
                                    CategoryResponseDTO.builder().categoryId(subCatList.getCategory().getCategoryId())
                                            .categoryName(subCatList.getCategory().getCategoryName()).build())
                            .removeSubCategory(subCatList.getRemoveSubCategory() == 1 ? true : false)
                            .subCategoryCreatedDate(subCatList.getSubCreatedDate())
                            .build())
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new SubCategoryException(
                    String.format(" Exception occured in get  subcategory for a category %s", e.getMessage()));
        }

    }

    @Override
    public SubCategoryResponseDTO updateSubCategory(SubCategoryRequestDTO requestDto) throws SubCategoryException {
        logger.info(" ::: SubCategoryLogic updateSubCategory starts ::: ");
        try {
            SubCategory getSubCatObj = subCategoryRepo.findBySubCategoryId(requestDto.getSubCategoryId());
            if (Objects.nonNull(getSubCatObj)) {
                if (Objects.nonNull(requestDto.getSubCategoryName()))
                    getSubCatObj.setSubCategoryName(requestDto.getSubCategoryName());
                if (Objects.nonNull(requestDto.isRemoveSubCategory()))
                    getSubCatObj.setRemoveSubCategory(requestDto.isRemoveSubCategory() ? 1 : 0);
                return SubCategoryResponseDTO.builder()
                        .subCategoryId(getSubCatObj.getSubCategoryId())
                        .subCategoryName(getSubCatObj.getSubCategoryName())
                        .categoryDetails(
                                CategoryResponseDTO.builder().categoryId(getSubCatObj.getCategory().getCategoryId())
                                        .categoryName(getSubCatObj.getCategory().getCategoryName()).build())
                        .removeSubCategory(getSubCatObj.getRemoveSubCategory() == 1 ? true : false)
                        .subCategoryCreatedDate(getSubCatObj.getSubCreatedDate()).build();
            } else {
                throw new SubCategoryException(
                        String.format(" SubCategory %s not found", requestDto.getSubCategoryId()));
            }
        } catch (Exception e) {
            throw new SubCategoryException(
                    String.format(" Exception occured while updating sub category %s", e.getMessage()));
        }

    }

    @Override
    public void deleteSubCategory(String subCategoryId) throws SubCategoryException {
        logger.info(" ::: SubCategoryLogic deleteSubCategory starts ::: ");
        try {
            SubCategory getSubCatObj = subCategoryRepo.findBySubCategoryId(subCategoryId);
            if (Objects.nonNull(getSubCatObj)) {
                subCategoryRepo.delete(getSubCatObj);
            } else {
                throw new SubCategoryException(
                        String.format(" SubCategory %s not found", subCategoryId));
            }
        } catch (Exception e) {
            throw new SubCategoryException(
                    String.format(" Exception occured while deleting sub category %s", e.getMessage()));
        }

    }

    /**
     *
     * @return String with Subcategory Id
     */
    private String generateSubCategoryId() throws SubCategoryException {
        logger.info(" ::: generateSubCategoryId :::");
        String subCategoryId = "";
        try {
            SubCategory getSubCategoryId = subCategoryRepo.findTopByOrderBySubCreatedDateDesc();
            if (Objects.isNull(getSubCategoryId)) {
                subCategoryId = SubCategoryConstant.SUB_CATEGORY_ID_PREFIX + 1;
            } else {
                Integer val = Integer.parseInt(subCategoryId.substring(3));
                logger.info(" ::: Val :::", val);
                Integer finalVal = val + 1;
                subCategoryId = SubCategoryConstant.SUB_CATEGORY_ID_PREFIX + finalVal;
            }
        } catch (Exception e) {
            throw new SubCategoryException("Exception in generateSubCategoryId" + e.getMessage());
        }
        logger.info(" ::: subCategoryId ::: %s", subCategoryId);
        return subCategoryId;
    }

}
