package com.expensemanager.customexception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SubCategoryException extends RuntimeException {

    public SubCategoryException(String msg) {
        super(msg);
    }
}
