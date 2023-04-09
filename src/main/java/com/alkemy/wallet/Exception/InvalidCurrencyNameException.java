package com.alkemy.wallet.Exception;

public class InvalidCurrencyNameException extends RuntimeException{

    public InvalidCurrencyNameException(String message) {
        super(message);
    }
}
