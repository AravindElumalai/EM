package com.expensemanager.abstractlayer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.expensemanager.customexception.IncomeExpection;
import com.expensemanager.dto.IncomeRequestDto;
import com.expensemanager.dto.IncomeResponseDto;

@Service
public interface IncomeAbstract {

    public IncomeResponseDto addEntry(IncomeRequestDto request) throws IncomeExpection;

    public IncomeResponseDto updateEntry(IncomeRequestDto request) throws IncomeExpection;

    public void deleteEntry(String incomeId) throws IncomeExpection;

    /* Get Methods Avaliable */
    public List<IncomeResponseDto> getEntryCurrentMonthAndYear() throws IncomeExpection;

    public List<IncomeResponseDto> getEntryBetweenStartAndEndDate(IncomeRequestDto request) throws IncomeExpection;

    public IncomeResponseDto getOneIncomeById(String incomeId) throws IncomeExpection;

    public List<IncomeResponseDto> getEntryBetweenMonthAndYear(IncomeRequestDto request) throws IncomeExpection;

    public List<IncomeResponseDto> getEntryForYear(int year) throws IncomeExpection;

    public List<IncomeResponseDto> getEntryForMonth(int month) throws IncomeExpection;

}
