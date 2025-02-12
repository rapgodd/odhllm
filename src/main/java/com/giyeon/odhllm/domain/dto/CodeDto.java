package com.giyeon.odhllm.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeDto {
    private String email;
    private String code;

    public void fillMailAndCode(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
