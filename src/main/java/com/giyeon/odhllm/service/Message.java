package com.giyeon.odhllm.service;

import com.giyeon.odhllm.domain.Chat;

import java.util.List;

public interface Message {
    boolean saveAll(List<Chat> chat);

}
