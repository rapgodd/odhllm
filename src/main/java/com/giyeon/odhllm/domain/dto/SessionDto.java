package com.giyeon.odhllm.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionDto {
    private Long Id;
    private String email;
    private String nickname;
}
