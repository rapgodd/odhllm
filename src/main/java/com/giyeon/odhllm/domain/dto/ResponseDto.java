package com.giyeon.odhllm.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto<T> {

    private final int code;
    private final T data;
    private final String errorMessage;


}
