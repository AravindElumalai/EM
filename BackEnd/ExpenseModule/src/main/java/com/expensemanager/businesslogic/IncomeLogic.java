package com.expensemanager.businesslogic;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensemanager.Constant.IncomeConstant;
import com.expensemanager.abstractlayer.IncomeAbstract;
import com.expensemanager.customexception.IncomeExpection;
import com.expensemanager.domain.Income;
import com.expensemanager.dto.IncomeRequestDto;
import com.expensemanager.dto.IncomeResponseDto;
import com.expensemanager.dto.SubCategoryResponseDTO;
import com.expensemanager.repository.IncomeRepository;
import com.expensemanager.repository.SubCategoryRepository;

@Service
public class IncomeLogic implements IncomeAbstract {

    private Logger logger = LoggerFactory.getLogger(IncomeLogic.class);

    @Autowired
    private IncomeRepository incomeRepo;

    @Autowired
    private SubCategoryRepository subCatRepo;

    @Override
    public IncomeResponseDto addEntry(IncomeRequestDto request) throws IncomeExpection {
        logger.info("::: IncomeLogic ::: addEntry ::::");
        try {
            Income addObj = Income.builder().incomeId(generateIncomeId()).incomeName(request.getIncomeName())
                    .subCategory(subCatRepo.findBySubCategoryId(request.getSubCategoryId())).amount(request.getAmount())
                    .comment(request.getComment()).incomeReceivedDate(new Date())
                    .incomeMonth(getMonthFromDate(new Date().toString()))
                    .incomeYear(getYearFromDate(new Date().toString())).build();
            addObj = incomeRepo.saveAndFlush(addObj);
            return generateResponse(addObj);

        } catch (Exception e) {
            throw new IncomeExpection(" Error in addEntry Method " + e.getMessage());
        }

    }

    @Override
    public IncomeResponseDto updateEntry(IncomeRequestDto request) throws IncomeExpection {
        logger.info("::: IncomeLogic ::: updateEntry ::::");
        try {
            Income getIncome = incomeRepo.findByIncomeId(request.getIncomeId());
            if (Objects.nonNull(getIncome)) {
                if (Objects.nonNull(request.getIncomeName()))
                    getIncome.setIncomeName(request.getIncomeName());
                if (Objects.nonNull(request.getSubCategoryId()))
                    getIncome.setSubCategory(subCatRepo.findBySubCategoryId(request.getSubCategoryId()));
                if (Objects.nonNull(request.getComment()))
                    getIncome.setComment(request.getComment());
                if (Objects.nonNull(request.getAmount()))
                    getIncome.setAmount(request.getAmount());
                if (Objects.nonNull(request.getYear()))
                    getIncome.setIncomeYear(request.getYear());
                if (Objects.nonNull(request.getMonth()))
                    getIncome.setIncomeMonth(request.getMonth());
                if (Objects.nonNull(request.getModifyDate()))
                    getIncome.setIncomeReceivedDate(request.getModifyDate());
                incomeRepo.saveAndFlush(getIncome);
                return generateResponse(getIncome);
            } else {
                throw new IncomeExpection(String.format("Income %s is not found", request.getIncomeId()));
            }
        } catch (Exception e) {
            throw new IncomeExpection(String.format("Exception occured in update entry %s", e.getMessage()));
        }

    }

    @Override
    public void deleteEntry(String incomeId) throws IncomeExpection {
        logger.info("::: IncomeLogic ::: deleteEntry ::::");
        try {
            Income getIncome = incomeRepo.findByIncomeId(incomeId);
            if (Objects.nonNull(getIncome)) {
                incomeRepo.delete(getIncome);
            } else {
                throw new IncomeExpection(String.format("Income %s is not found", incomeId));
            }
        } catch (Exception e) {
            throw new IncomeExpection(String.format("Exception occured in delete entry %s", e.getMessage()));
        }

    }

    @Override
    public List<IncomeResponseDto> getEntryCurrentMonthAndYear() throws IncomeExpection {
        logger.info("::: IncomeLogic ::: getEntryCurrentMonthAndYear ::::");
        try {
            List<Income> getCurrentMonthIncomeList = incomeRepo.findByIncomeMonthAndIncomeYear(
                    getMonthFromDate(new Date().toString()), getYearFromDate(new Date().toString()));
            if (!getCurrentMonthIncomeList.isEmpty()) {
                return getCurrentMonthIncomeList.stream().map(currentObj -> generateResponse(currentObj))
                        .collect(Collectors.toList());
            } else {
                throw new IncomeExpection("No Income found for the current month");
            }
        } catch (Exception e) {
            throw new IncomeExpection(
                    String.format("Exception occured in getEntryCurrentMonthAndYear   %s", e.getMessage()));
        }

    }

    @Override
    public List<IncomeResponseDto> getEntryBetweenStartAndEndDate(IncomeRequestDto request) throws IncomeExpection {
        logger.info("::: IncomeLogic ::: getEntryBetweenStartAndEndDate ::::");
        try {
            List<Income> getIncomeList = incomeRepo.findByIncomeReceivedDateBetween(request.getStartDate(),
                    request.getEndDate());
            if (!getIncomeList.isEmpty()) {
                return getIncomeList.stream().map(currentObj -> generateResponse(currentObj))
                        .collect(Collectors.toList());
            } else {
                throw new IncomeExpection(String.format("No income found between %s to %s", request.getStartDate(),
                        request.getEndDate()));
            }
        } catch (Exception e) {
            throw new IncomeExpection(
                    String.format("Exception occured in getEntryBetweenStartAndEndDate   %s", e.getMessage()));
        }
    }

    @Override
    public IncomeResponseDto getOneIncomeById(String incomeId) throws IncomeExpection {
        logger.info("::: IncomeLogic ::: getOneIncomeById ::::");
        try {
            Income getIncome = incomeRepo.findByIncomeId(incomeId);
            if (Objects.nonNull(getIncome)) {
                return generateResponse(getIncome);
            } else {
                throw new IncomeExpection(String.format("No income found in data base for %s", incomeId));
            }
        } catch (Exception e) {
            throw new IncomeExpection(
                    String.format("Exception occured in getOneIncomeById   %s", e.getMessage()));
        }
    }

    @Override
    public List<IncomeResponseDto> getEntryBetweenMonthAndYear(IncomeRequestDto request) throws IncomeExpection {
        logger.info("::: IncomeLogic ::: getEntryBetweenMonthAndYear ::::");
        try {
            List<Income> getIncomeList = incomeRepo.findByIncomeMonthAndIncomeYear(request.getMonth(),
                    request.getYear());
            if (!getIncomeList.isEmpty()) {
                return getIncomeList.stream().map(currentObj -> generateResponse(currentObj))
                        .collect(Collectors.toList());
            } else {
                throw new IncomeExpection(
                        String.format("No income found for the month %s and year %s", request.getMonth(),
                                request.getYear()));
            }
        } catch (Exception e) {
            throw new IncomeExpection(
                    String.format("Exception occured in getEntryBetweenMonthAndYear   %s", e.getMessage()));
        }
    }

    @Override
    public List<IncomeResponseDto> getEntryForYear(int year) throws IncomeExpection {
        logger.info("::: IncomeLogic ::: getEntryForYear ::::");
        try {
            List<Income> getIncomeList = incomeRepo.findByIncomeYear(year);
            if (!getIncomeList.isEmpty()) {
                return getIncomeList.stream().map(currentObj -> generateResponse(currentObj))
                        .collect(Collectors.toList());
            } else {
                throw new IncomeExpection(
                        String.format("No income found for the year %s", year));
            }
        } catch (Exception e) {
            throw new IncomeExpection(
                    String.format("Exception occured in getEntryForYear   %s", e.getMessage()));
        }
    }

    @Override
    public List<IncomeResponseDto> getEntryForMonth(String month) throws IncomeExpection {
        logger.info("::: IncomeLogic ::: getEntryForMonth ::::");
        try {
            List<Income> getIncomeList = incomeRepo.findByIncomeMonth(month);
            if (!getIncomeList.isEmpty()) {
                return getIncomeList.stream().map(currentObj -> generateResponse(currentObj))
                        .collect(Collectors.toList());
            } else {
                throw new IncomeExpection(
                        String.format("No income found for the Month %s", month));
            }
        } catch (Exception e) {
            throw new IncomeExpection(
                    String.format("Exception occured in getEntryForMonth   %s", e.getMessage()));
        }
    }

    private String generateIncomeId() throws IncomeExpection {
        logger.info(" ::: IncomeLogic ::: generateIncomeId :::");
        String incomeId = "";
        try {
            Income incomeObj = incomeRepo.findTopByOrderByIncomeReceivedDateDesc();
            logger.info(" ::: Income ID :::" + incomeObj.getIncomeId());
            if (Objects.isNull(incomeObj)) {
                logger.info(" ::: Object is Null :::");
                incomeId = IncomeConstant.INCOME_PREFIX + 1;
            } else {
                logger.info(" ::: Object is Not Null :::");
                String existingId = incomeObj.getIncomeId();
                Integer val = Integer.parseInt(existingId.substring(3));
                logger.info(" ::: Val :::", val);
                Integer finalVal = val + 1;
                incomeId = IncomeConstant.INCOME_PREFIX + finalVal;
            }
            return incomeId;
        } catch (Exception e) {
            throw new IncomeExpection("Expection occured in generateIncomeId" + e.getMessage());
        }

    }

    private String getMonthFromDate(String date) {
        logger.info("::: getMonthFromDate :::");
        LocalDate currentDate = LocalDate.parse(date);
        String month = currentDate.getMonth().toString();
        logger.info("::: Month :::" + month);
        return month;
    }

    private int getYearFromDate(String date) {
        logger.info("::: getYearFromDate :::");
        LocalDate currentDate = LocalDate.parse(date);
        int year = currentDate.getYear();
        logger.info("::: year :::" + year);
        return year;
    }

    private IncomeResponseDto generateResponse(Income incomeObj) {
        logger.info(" ::: generateResponse :::");
        try {
            IncomeResponseDto incomeRes = null;
            if (Objects.nonNull(incomeObj)) {
                incomeRes = IncomeResponseDto.builder().incomeId(incomeObj.getIncomeId())
                        .incomeName(incomeObj.getIncomeName())
                        .subcategory(
                                SubCategoryResponseDTO.builder()
                                        .subCategoryId(incomeObj.getSubCategory().getSubCategoryId())
                                        .subCategoryName(incomeObj.getSubCategory().getSubCategoryName()).build())
                        .amount(incomeObj.getAmount()).comment(incomeObj.getComment()).month(incomeObj.getIncomeMonth())
                        .year(incomeObj.getIncomeYear()).build();
            }
            return incomeRes;
        } catch (Exception e) {
            throw new IncomeExpection(" Error in generateResponse Method " + e.getMessage());
        }
    }
}
