package com.giyeon.odhllm.domain.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ResponseDto<T> {

    private final int code;
    private final T data;
    private final String errorMessage;

    public static <T> ResponseEntity<ResponseDto<?>> ok(T data) {
        return ResponseEntity
                .ok()
                .body(ResponseDto
                        .<T>builder()
                        .code(HttpStatus.OK.value())
                        .data(data)
                        .build());
    }



}
