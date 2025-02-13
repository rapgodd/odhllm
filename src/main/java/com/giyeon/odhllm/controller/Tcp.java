package com.giyeon.odhllm.controller;

import com.giyeon.odhllm.domain.dto.MessageDto;
import com.giyeon.odhllm.domain.dto.ResponseMessageDto;

public interface Tcp {
    ResponseMessageDto send(MessageDto message);

}
