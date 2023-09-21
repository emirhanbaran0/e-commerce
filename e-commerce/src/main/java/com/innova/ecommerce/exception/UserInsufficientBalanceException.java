package com.innova.ecommerce.exception;

public class UserInsufficientBalanceException extends RuntimeException{

    public UserInsufficientBalanceException(String message) {
        super(message);
    }
}
