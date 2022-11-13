package com.expensemanager.businesslogic;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Constant.CategoryConstant;
import com.expensemanager.abstractlayer.CategoryAbstract;
import com.expensemanager.customexception.CategoryExpection;
import com.expensemanager.domain.Category;
import com.expensemanager.dto.CategoryRequestDTO;
import com.expensemanager.dto.CategoryResponseDTO;
import com.expensemanager.repository.CategoryRepository;

@Service
public class CategoryLogic implements CategoryAbstract{
    
    private Logger logger = LoggerFactory.getLogger(CategoryLogic.class) ;

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequest) throws CategoryExpection {
        logger.info("::createCategory Starts ::");
        try{
            Category saveCat = Category.builder().
            categoryId(getCatId()).categoryName(categoryRequest.getCategoryName()).removeCategory(categoryRequest.isRemoveCategory() ? 1 : 0).
            createDate(new Date()).build();
            saveCat = categoryRepo.saveAndFlush(saveCat);
            return CategoryResponseDTO.builder().categoryId(saveCat.getCategoryId()).categoryName(saveCat.getCategoryName()).
            removeCategory(saveCat.getRemoveCategory() == 1 ? true : false).createdDate(saveCat.getCreateDate()).build();
        }catch(Exception e){
            throw new CategoryExpection(e.getMessage());
        }
       
    }

    @Override
    public CategoryResponseDTO getCategory(String categoryId) throws CategoryExpection {
        logger.info("::getCategory Starts ::");
        try {
            Category getCatbyId = categoryRepo.findByCategoryId(categoryId);
      
            if(Objects.nonNull(getCatbyId)){
                return CategoryResponseDTO.builder().categoryId(getCatbyId.getCategoryId()).categoryName(getCatbyId.getCategoryName()).
                removeCategory(getCatbyId.getRemoveCategory() == 1 ? true : false).createdDate(getCatbyId.getCreateDate()).build();
            }else{
                throw new CategoryExpection(String.format("Category %s not found", categoryId));
            }
        } catch (Exception e) {
            throw new CategoryExpection(e.getMessage());
        }
        
       
    }

    @Override
    public List<CategoryResponseDTO> getAllCategory() throws CategoryExpection {
        logger.info("::getAllCategory Starts ::");
        try {
            List<Category> allCategory = categoryRepo.findAll();
            if(allCategory.isEmpty()){
                throw new CategoryExpection("No Category Available");
            }else{
                return allCategory.stream().map(alCat -> {
                    return CategoryResponseDTO.builder().categoryId(alCat.getCategoryId()).categoryName(alCat.getCategoryName())
                    .removeCategory(alCat.getRemoveCategory() == 1 ? true : false).createdDate(alCat.getCreateDate()).build();
                }).collect(Collectors.toList());
            }
        } catch (Exception e) {
            throw new CategoryExpection(e.getMessage());
        }
        
      
    }

    @Override
    
    public CategoryResponseDTO updateCategory(CategoryRequestDTO categoryRequest) throws CategoryExpection {
        logger.info("::updateCategory Starts ::");
        try{
            Category getCategoryObj = categoryRepo.findByCategoryId(categoryRequest.getCategoryId());
            if(Objects.nonNull(getCategoryObj)){
                getCategoryObj.setCategoryName(categoryRequest.getCategoryName());
                getCategoryObj.setRemoveCategory(categoryRequest.isRemoveCategory() ? 1: 0);
                categoryRepo.save(getCategoryObj);
                return CategoryResponseDTO.builder().categoryId(getCategoryObj.getCategoryId()).categoryName(getCategoryObj.getCategoryName())
                .removeCategory(getCategoryObj.getRemoveCategory() == 1 ? true: false).createdDate(getCategoryObj.getCreateDate()).build();
                
            }else{
                throw new CategoryExpection(String.format("Category %s is found",categoryRequest.getCategoryId()));
            }
        }catch(Exception e){
            throw new CategoryExpection(e.getMessage());
        }
        
       
    }

    @Override
    public void deleteCategory(String categoryId) throws CategoryExpection {
        logger.info("::deleteCategory Starts ::");
        try{
            Category getCategoryObj = categoryRepo.findByCategoryId(categoryId);
            if(Objects.nonNull(getCategoryObj)){
                categoryRepo.delete(getCategoryObj);
                
            }else{
                throw new CategoryExpection(String.format("Category %s is found",categoryId));
            }
        }catch(Exception e){
            throw new CategoryExpection(e.getMessage());
        }

    }

    @Override
    public CategoryResponseDTO blockCategory(CategoryRequestDTO categoryRequest) throws CategoryExpection {
        logger.info("::blockCategory Starts ::");
        try{
            Category getCategoryObj = categoryRepo.findByCategoryId(categoryRequest.getCategoryId());
            if(Objects.nonNull(getCategoryObj)){
                getCategoryObj.setRemoveCategory(categoryRequest.isRemoveCategory() ? 1 : 0); 
                categoryRepo.save(getCategoryObj);
                return CategoryResponseDTO.builder().categoryId(getCategoryObj.getCategoryId()).categoryName(getCategoryObj.getCategoryName())
                .removeCategory(getCategoryObj.getRemoveCategory() == 1 ? true: false).createdDate(getCategoryObj.getCreateDate()).build();
                
            }else{
                throw new CategoryExpection(String.format("Category %s is found",categoryRequest.getCategoryId()));
            }
        }catch(Exception e){
            throw new CategoryExpection(e.getMessage());
        }

    }
    
    /**
     * This method will generate category id with prefix of CAT, Example(CAT1,CAT2,CAT3)
     * @return String with Category Id
     */
    private String getCatId(){
        logger.info("::: Get Category Id :::");
        String catId = "";
        try{
            Category lastRecord = categoryRepo.findTopByOrderByCreateDateDesc();
            if(lastRecord != null){
                logger.info(":::lastRecord is not null:::"+lastRecord.getCategoryId());
            }
            if(Objects.isNull(lastRecord)){
                logger.info("::: Get Category Id Object is Null:::");
                catId = CategoryConstant.CATEGORY_PREFIX+1;
            }else{
                logger.info("::: Get Category Id Object is Not Null:::");
                String getCatId = lastRecord.getCategoryId();
                Integer val = Integer.parseInt(getCatId.substring(3));
                logger.info(" ::: Val :::",val);
                Integer finalVal = val+1;
                catId = CategoryConstant.CATEGORY_PREFIX+finalVal;
            }
            logger.info("::CategoryID::"+catId);
            return catId;
        }catch(Exception e){
            throw new CategoryExpection(e.getMessage());
        }

    }


}
