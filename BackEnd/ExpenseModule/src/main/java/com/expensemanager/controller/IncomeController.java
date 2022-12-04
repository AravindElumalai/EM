package com.expensemanager.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensemanager.abstractlayer.IncomeAbstract;
import com.expensemanager.dto.IncomeRequestDto;
import com.expensemanager.dto.IncomeResponseDto;
import com.expensemanager.dto.SubCategoryResponseDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/income", produces = MediaType.APPLICATION_JSON_VALUE)
public class IncomeController {

    private Logger logger = LoggerFactory.getLogger(IncomeController.class);
    @Autowired
    private IncomeAbstract incomeAbstract;

    @PostMapping(value = "/addIncome", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomeResponseDto> addIncome(@RequestBody IncomeRequestDto entity) {
        logger.info(" ::: IncomeController ::: addIncome :::");
        IncomeResponseDto response = incomeAbstract.addEntry(entity);
        if (Objects.nonNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/modifyIncome", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IncomeResponseDto> updateIncomeDetails(@RequestBody IncomeRequestDto entity) {
        logger.info(" ::: IncomeController ::: updateIncomeDetails :::");
        IncomeResponseDto response = incomeAbstract.updateEntry(entity);
        if (Objects.nonNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteIncome/{incomeId}")
    public ResponseEntity<SubCategoryResponseDTO> deleteIncomeDetails(@PathVariable String incomeId) {
        logger.info(" ::: IncomeController ::: deleteIncomeDetails :::");
        incomeAbstract.deleteEntry(incomeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getCurrentMonthExpense")
    public ResponseEntity<List<IncomeResponseDto>> currentMonthIncome() {
        logger.info(" ::: IncomeController ::: currentMonthIncome :::");
        List<IncomeResponseDto> response = incomeAbstract.getEntryCurrentMonthAndYear();
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getBetweenDate")
    public ResponseEntity<List<IncomeResponseDto>> currentIncomeBetweenDate(@RequestBody IncomeRequestDto entity) {
        logger.info(" ::: IncomeController ::: currentIncomeBetweenDate :::");
        List<IncomeResponseDto> response = incomeAbstract.getEntryBetweenStartAndEndDate(entity);
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getExpense/{incomeId}")
    public ResponseEntity<IncomeResponseDto> getIncomeById(@PathVariable String incomeId) {
        logger.info(" ::: IncomeController ::: getIncomeById :::");
        IncomeResponseDto response = incomeAbstract.getOneIncomeById(incomeId);
        if (Objects.nonNull(response)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping(value = "/getMonthAndYear")
    public ResponseEntity<List<IncomeResponseDto>> incomeBetweenMonthAndYear(@RequestBody IncomeRequestDto entity) {
        logger.info(" ::: IncomeController ::: incomeBetweenMonthAndYear :::");
        List<IncomeResponseDto> response = incomeAbstract.getEntryBetweenMonthAndYear(entity);
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getYear/{year}")
    public ResponseEntity<List<IncomeResponseDto>> incomeInYear(@PathVariable int year) {
        logger.info(" ::: IncomeController ::: incomeInYear :::");
        List<IncomeResponseDto> response = incomeAbstract.getEntryForYear(year);
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getMonth/{month}")
    public ResponseEntity<List<IncomeResponseDto>> incomeInMonth(@PathVariable int month) {
        logger.info(" ::: IncomeController ::: incomeInMonth :::");
        List<IncomeResponseDto> response = incomeAbstract.getEntryForMonth(month);
        if (response.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

}
