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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensemanager.abstractlayer.SubCategoryAbstract;
import com.expensemanager.dto.SubCategoryRequestDTO;
import com.expensemanager.dto.SubCategoryResponseDTO;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(value = "/subcategory")
public class SubCategoryController {

    private Logger logger = LoggerFactory.getLogger(SubCategoryController.class);

    @Autowired
    private SubCategoryAbstract subCategoryService;

    @PostMapping(path = "/addSubcategory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubCategoryResponseDTO> createSubCategory(@RequestBody SubCategoryRequestDTO requestDTO) {
        logger.info("::: Inside SubCategoryController createSubCategory :::");

        SubCategoryResponseDTO response = subCategoryService.createSubCategory(requestDTO);
        if (Objects.nonNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "/getSubCategory/{subcategoryid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubCategoryResponseDTO> getSubCategory(@PathVariable String subcategoryid) {
        logger.info("::: Inside SubCategoryController getSubCategory :::");
        SubCategoryResponseDTO response = subCategoryService.getSubCategory(subcategoryid);
        if (Objects.nonNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/showall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubCategoryResponseDTO>> showAllSubCategory() {
        logger.info("::: Inside SubCategoryController showAllSubCategory :::");
        List<SubCategoryResponseDTO> response = subCategoryService.getAllSubCategory();
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/getonecategory/{categoryid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubCategoryResponseDTO>> getSubCategoryForCategory(@PathVariable String categoryid) {
        logger.info("::: Inside SubCategoryController getSubCategoryForCategory :::");
        List<SubCategoryResponseDTO> response = subCategoryService.getAllSubCategoryForCategory(categoryid);
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @PutMapping(value = "updateSub", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubCategoryResponseDTO> updateSubCategory(@RequestBody SubCategoryRequestDTO requestDTO) {
        logger.info("::: Inside SubCategoryController updateSubCategory :::");
        SubCategoryResponseDTO response = subCategoryService.updateSubCategory(requestDTO);
        if (Objects.nonNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/deleteSubCategory/{subcategoryid}")
    public ResponseEntity<SubCategoryResponseDTO> removeCategory(@PathVariable String subcategoryid) {
        logger.info("::: Inside SubCategoryController removeCategory :::");
        subCategoryService.deleteSubCategory(subcategoryid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
