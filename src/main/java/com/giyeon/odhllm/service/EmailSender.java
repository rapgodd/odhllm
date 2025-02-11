package com.giyeon.odhllm.service;

import com.giyeon.odhllm.domain.dto.MailVerificationDto;


public interface EmailSender {

    String send(MailVerificationDto details);

}
