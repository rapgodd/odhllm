package com.giyeon.odhllm.exception.custom;

public class WrongRefreshToken extends RuntimeException{

    public WrongRefreshToken(String message) {
        super(message);
    }
}
