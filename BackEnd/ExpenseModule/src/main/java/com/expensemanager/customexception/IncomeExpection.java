package com.expensemanager.customexception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IncomeExpection extends RuntimeException {

    public IncomeExpection(String msg) {
        super(msg);
    }

}
