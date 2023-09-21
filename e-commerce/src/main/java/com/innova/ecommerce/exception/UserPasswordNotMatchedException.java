package com.innova.ecommerce.exception;

public class UserPasswordNotMatchedException extends RuntimeException{

    public UserPasswordNotMatchedException(String message) {
        super(message);
    }
}
