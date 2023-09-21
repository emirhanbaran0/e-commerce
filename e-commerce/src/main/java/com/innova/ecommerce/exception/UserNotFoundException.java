package com.innova.ecommerce.exception;

public class UserNotFoundException extends  RuntimeException{
        public UserNotFoundException(String message) {
            super(message);
        }
    }
