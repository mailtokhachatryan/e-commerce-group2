package com.smartcode.ecommerce.exceptions;

public class ProductValidationException extends  RuntimeException{
    public ProductValidationException(String message) {
        super(message);
    }
}
