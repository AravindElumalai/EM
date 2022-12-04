package com.expensemanager.Utils;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DateUtils {

    private Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private int getMonthFromDate(Date date) {
        logger.info("::: getMonthFromDate :::");
        logger.info("::: getMonthFromDate :::" + date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        logger.info("::: Month :::" + month);
        return month;
    }

    private int getYearFromDate(Date date) {
        logger.info("::: getYearFromDate :::");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        logger.info("::: year :::" + year);
        return year;
    }
}
