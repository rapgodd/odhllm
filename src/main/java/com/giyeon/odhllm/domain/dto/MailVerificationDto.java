package com.giyeon.odhllm.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailVerificationDto {

    private String email;
    private String code;
    private String msgBody;

}
