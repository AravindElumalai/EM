package com.expensemanager.abstractlayer;

import org.springframework.stereotype.Service;

import com.expensemanager.customexception.ExpenseExpection;
import com.expensemanager.dto.ExpenseRequestDto;
import com.expensemanager.dto.ExpenseResponseDto;

@Service
public interface ExpenseAbstract {

    public ExpenseResponseDto addEntry(ExpenseRequestDto request) throws ExpenseExpection;

    public ExpenseResponseDto updateEntry(ExpenseRequestDto request) throws ExpenseExpection;

    public void deleteEntry(String incomeId) throws ExpenseExpection;

    /* Get Methods Avaliable */
    public List<ExpenseResponseDto> getEntryCurrentMonthAndYear() throws ExpenseExpection;

    public List<ExpenseResponseDto> getEntryBetweenStartAndEndDate(ExpenseRequestDto request) throws ExpenseExpection;

    public ExpenseResponseDto getOneIncomeById(String incomeId) throws ExpenseExpection;

    public List<ExpenseResponseDto> getEntryBetweenMonthAndYear(ExpenseRequestDto request) throws ExpenseExpection;

    public List<ExpenseResponseDto> getEntryForYear(int year) throws ExpenseExpection;

    public List<ExpenseResponseDto> getEntryForMonth(int month) throws ExpenseExpection;
}
