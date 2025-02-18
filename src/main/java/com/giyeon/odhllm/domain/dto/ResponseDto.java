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

    public static <T> ResponseEntity<ResponseDto<?>> duplicatedInput(T data) {
        return ResponseEntity
                .badRequest()
                .body(ResponseDto
                        .<T>builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .data(data)
                        .build());
    }

    public static <T> ResponseEntity<ResponseDto<?>> failedApiCall(T data) {
        return ResponseEntity
                .internalServerError()
                .body(ResponseDto
                        .<T>builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .data(data)
                        .build());
    }


}
