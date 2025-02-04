package com.giyeon.odhllm.account.domain;

import lombok.Data;

@Data
public class SignUpDto {
    public String nickname;
    public String email;
    public String password;

    public SignUpDto(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
