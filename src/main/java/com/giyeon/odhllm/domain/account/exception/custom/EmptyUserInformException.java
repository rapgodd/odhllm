package com.giyeon.odhllm.domain.account.exception.custom;

public class EmptyUserInformException extends RuntimeException {

    public EmptyUserInformException(String message) {
        super(message);
    }

}
