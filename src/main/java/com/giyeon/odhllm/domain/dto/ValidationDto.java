package com.giyeon.odhllm.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationDto {

    public String responseData;
    public boolean valid;

}
