package com.expensemanager.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensemanager.domain.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, String> {

    public Income findTopByOrderByIncomeReceivedDateDesc();

    public Income findByIncomeId(String incomeId);

    public List<Income> findByIncomeMonthAndIncomeYear(String month, int year);

    public List<Income> findByIncomeReceivedDateBetween(Date startDate, Date endDate);

    public List<Income> findByIncomeYear(int year);

    public List<Income> findByIncomeMonth(String month);
}
