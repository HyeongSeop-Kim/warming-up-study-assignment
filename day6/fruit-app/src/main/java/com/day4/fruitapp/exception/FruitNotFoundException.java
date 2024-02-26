package com.day4.fruitapp.exception;

import java.util.NoSuchElementException;

public class FruitNotFoundException extends NoSuchElementException {

    public FruitNotFoundException(String message) {
        super(message);
    }
}
