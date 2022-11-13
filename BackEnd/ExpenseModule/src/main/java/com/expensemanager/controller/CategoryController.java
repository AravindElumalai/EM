package com.expensemanager.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expensemanager.abstractlayer.CategoryAbstract;
import com.expensemanager.customexception.CategoryExpection;
import com.expensemanager.dto.CategoryRequestDTO;
import com.expensemanager.dto.CategoryResponseDTO;

@RestController
@RequestMapping(value="/category")
public class CategoryController {
    
    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryAbstract categoryService;

    @PostMapping(path="/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponseDTO> addCategory(@RequestBody CategoryRequestDTO categoryDto){
        logger.info(" :::Create Category Controller addCategory::: ");
        
        try{
            CategoryResponseDTO response =  categoryService.createCategory(categoryDto);
            if(response != null && Objects.nonNull(response)){
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }catch(CategoryExpection ce){
               throw ce;
        }
      
    }

    @GetMapping(path="/showAll", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryResponseDTO>> showAllCategory(){
        logger.info(" ::: Category Controller showAllCategory::: ");
       
        try{
            List<CategoryResponseDTO> response =  categoryService.getAllCategory();
            if(response != null && Objects.nonNull(response)){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch(CategoryExpection ce){
               throw ce;
        }
      
    }

    @GetMapping(path="/getCategory/{categoryId}")
    public ResponseEntity<CategoryResponseDTO> getParticularCategory(@PathVariable String categoryId){
        logger.info(" ::: Category Controller getParticularCategory::: ");
       
        try{
            CategoryResponseDTO response =  categoryService.getCategory(categoryId);
            if(response != null && Objects.nonNull(response)){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch(CategoryExpection ce){
               throw ce;
        }
      
    }

    @PutMapping(path="/updateCategory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponseDTO> updateCategory(@RequestBody CategoryRequestDTO category){
        logger.info(" ::: Category Controller updateCategory::: ");
        
        try{
            CategoryResponseDTO response =  categoryService.updateCategory(category);
            if(response != null && Objects.nonNull(response)){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch(CategoryExpection ce){
               throw ce;
        }
      
    }

    @PutMapping(path="/blockCategory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponseDTO> blockCategory(@RequestBody CategoryRequestDTO category){
        logger.info(" ::: Category Controller blockCategory::: ");
        
        try{
            CategoryResponseDTO response =  categoryService.blockCategory(category);
            if(response != null && Objects.nonNull(response)){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }catch(CategoryExpection ce){
               throw ce;
        }
      
    }   
    
    @DeleteMapping(path="/remove/{categoryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable String categoryId){
        logger.info(" ::: Category Controller deleteCategory::: ");
       
        try{
            categoryService.deleteCategory(categoryId);
            return new ResponseEntity<>(HttpStatus.OK);
            
        }catch(CategoryExpection ce){
               throw ce;
        }
      
    }     
}
