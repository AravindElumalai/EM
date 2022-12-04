package com.expensemanager.businesslogic;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensemanager.Constant.ExpenseConstant;
import com.expensemanager.abstractlayer.ExpenseAbstract;
import com.expensemanager.customexception.ExpenseExpection;
import com.expensemanager.domain.Expense;
import com.expensemanager.dto.ExpenseRequestDto;
import com.expensemanager.dto.ExpenseResponseDto;
import com.expensemanager.repository.ExpenseRepository;
import com.expensemanager.repository.SubCategoryRepository;

@Service
public class ExpenseLogic implements ExpenseAbstract {

    private Logger logger = LoggerFactory.getLogger(ExpenseLogic.class);

    @Autowired
    private ExpenseRepository expenseRepo;

    @Autowired
    private SubCategoryRepository subCatRepo;

    @Override
    public ExpenseResponseDto addEntry(ExpenseRequestDto request) throws ExpenseExpection {
        logger.info(" ::: ExpenseLogic ::: addEntry");
        Expense expenseObj = Expense.builder().expenseId(generateExpenseId()).expenseName(request.getExpenseName())
                .subCategory(subCatRepo.findBySubCategoryId(request.getSubCategoryId())).amount(request.getAmount())
                .expenseComment(request.getComment()).expenseReceivedDate(new Date()).build();
        return null;
    }

    @Override
    public ExpenseResponseDto updateEntry(ExpenseRequestDto request) throws ExpenseExpection {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteEntry(String incomeId) throws ExpenseExpection {
        // TODO Auto-generated method stub

    }

    @Override
    public List<ExpenseResponseDto> getEntryCurrentMonthAndYear() throws ExpenseExpection {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ExpenseResponseDto> getEntryBetweenStartAndEndDate(ExpenseRequestDto request) throws ExpenseExpection {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ExpenseResponseDto getOneIncomeById(String incomeId) throws ExpenseExpection {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ExpenseResponseDto> getEntryBetweenMonthAndYear(ExpenseRequestDto request) throws ExpenseExpection {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ExpenseResponseDto> getEntryForYear(int year) throws ExpenseExpection {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ExpenseResponseDto> getEntryForMonth(int month) throws ExpenseExpection {
        // TODO Auto-generated method stub
        return null;
    }

    private String generateExpenseId() throws ExpenseExpection {
        logger.info(" ::: ExpenseLogic ::: generateExpenseId :::");
        String expenseId = "";
        try {
            Expense expenseObj = expenseRepo.findTopByOrderByExpenseReceivedDateDesc();
            // logger.info(" ::: Income ID :::" + incomeObj.getIncomeId());
            if (Objects.isNull(expenseObj)) {
                logger.info(" ::: Object is Null :::");
                logger.info(" ::: ExpenseConstant.EXPENSE_PREFIX :::" + ExpenseConstant.EXPENSE_PREFIX);
                expenseId = ExpenseConstant.EXPENSE_PREFIX + 1;
                logger.info(" ::: expenseId Iddd :::" + expenseId);
            } else {
                logger.info(" ::: Object is Not Null :::");
                String existingId = expenseObj.getExpenseId();
                Integer val = Integer.parseInt(existingId.substring(3));
                logger.info(" ::: Val :::", val);
                Integer finalVal = val + 1;
                expenseId = ExpenseConstant.EXPENSE_PREFIX + finalVal;
            }
            logger.info(" ::: expenseId :::" + expenseId);
            return expenseId;
        } catch (Exception e) {
            throw new ExpenseExpection("Expection occured in generateExpenseId" + e.getMessage());
        }

    }
}
