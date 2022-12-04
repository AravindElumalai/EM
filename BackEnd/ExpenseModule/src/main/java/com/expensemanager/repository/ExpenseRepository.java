package com.expensemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expensemanager.domain.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {

    public Expense findTopByOrderByExpenseReceivedDateDesc();
}
