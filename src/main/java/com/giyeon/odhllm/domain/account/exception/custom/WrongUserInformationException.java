package com.giyeon.odhllm.domain.account.exception.custom;

public class WrongUserInformationException extends RuntimeException{

    public WrongUserInformationException(String message) {
        super(message);
    }
}
