package com.giyeon.odhllm.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthTokenDto {
    private Long userId;
    private boolean existedUser;
    private String email;
    private String nickname;

}