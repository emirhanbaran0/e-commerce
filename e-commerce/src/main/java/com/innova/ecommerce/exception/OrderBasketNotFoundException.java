package com.innova.ecommerce.exception;

public class OrderBasketNotFoundException extends RuntimeException{

    public OrderBasketNotFoundException(String message) {
        super(message);
    }
}
