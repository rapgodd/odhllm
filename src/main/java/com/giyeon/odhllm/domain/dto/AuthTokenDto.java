package com.giyeon.odhllm.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class AuthTokenDto {
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long userId;
}