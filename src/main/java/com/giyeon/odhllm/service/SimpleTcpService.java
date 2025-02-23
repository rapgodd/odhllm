package com.giyeon.odhllm.service;

import com.giyeon.odhllm.controller.Tcp;
import com.giyeon.odhllm.domain.Chat;
import com.giyeon.odhllm.domain.ChatRoom;
import com.giyeon.odhllm.domain.User;
import com.giyeon.odhllm.domain.dto.MessageDto;
import com.giyeon.odhllm.domain.dto.ResponseMessageDto;
import com.giyeon.odhllm.repository.ChatRoomRepository;
import com.giyeon.odhllm.repository.MessageEmRepository;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SimpleTcpService implements Tcp {

    @Value("${fast-server.ip}")
    private String FAST_SERVER_IP;
    private final MessageEmRepository messageEmRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    @Transactional
    public ResponseMessageDto send(MessageDto message) {

        setTagIn(message);
        ResponseMessageDto llmResponse = getAiAnswer(message);

        //chat 엔티티 생성 , 저장
        Chat userMessage = Chat.of(message.getChatRoomId(), new User(message.getSender()), message.getMessage());
        Chat llmMessage = Chat.of(message.getChatRoomId(), null, llmResponse.getMessage());
        messageEmRepository.saveAll(List.of(userMessage, llmMessage));

        llmResponse.makeMoreReadable();
        return llmResponse;
    }

    private ResponseMessageDto getAiAnswer(MessageDto message) {
        RestTemplate http = new RestTemplate();
        String url = "http://" + FAST_SERVER_IP + "/llm";
        return http.postForObject(url, message, ResponseMessageDto.class);
    }

    private void setTagIn(MessageDto message) {
        ChatRoom roomById = chatRoomRepository.findRoomById(message.getChatRoomId());
        String topic = String.valueOf(roomById.getTopic());
        message.setTopic(topic);
    }

}
