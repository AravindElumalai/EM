package com.expensemanager.customexception;

public class CategoryExpection extends RuntimeException{
    
    public CategoryExpection(String msg){
        super(msg);
    }
}
