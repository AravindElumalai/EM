package com.expensemanager.customexception;

public class ExpenseExpection extends RuntimeException {

    public ExpenseExpection(String msg) {
        super(msg);
    }

}
