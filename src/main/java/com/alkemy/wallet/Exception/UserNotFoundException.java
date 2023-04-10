package com.alkemy.wallet.Exception;

public class UserNotFoundException extends Exception{
    private final static String USER_DOESNT_EXIST = "User with %s id doesn't exist";

    public UserNotFoundException(Long id){

        super(String.format(USER_DOESNT_EXIST, id));
    }
}
