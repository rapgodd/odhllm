package com.giyeon.odhllm.domain.dto;

import lombok.Data;

@Data
public class ResponseMessageDto {
    private String message;

    public ResponseMessageDto(String message) {
        this.message = message;
    }
}
