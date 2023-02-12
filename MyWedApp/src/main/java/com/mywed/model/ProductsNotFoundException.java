package com.mywed.model;

public class ProductsNotFoundException extends Throwable{
    public ProductsNotFoundException(String message) {
        super(message);
    }
}
