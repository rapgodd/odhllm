package com.giyeon.odhllm.exception.custom;

public class WrongUserInformationException extends RuntimeException{

    public WrongUserInformationException(String message) {
        super(message);
    }
}
