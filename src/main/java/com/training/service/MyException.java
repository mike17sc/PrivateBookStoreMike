package com.training.service;

/**
 * Created by Mschneider on 13-06-17.
 */
public class MyException extends Exception {
    /**
     *
     * @param message throw the message you insert
     */
    public MyException(String message){
        super(message);
    }
}
