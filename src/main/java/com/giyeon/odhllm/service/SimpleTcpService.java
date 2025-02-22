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

        //방의 Topic Dto에 넣기
        ChatRoom roomById = chatRoomRepository.findRoomById(message.getChatRoomId());
        String topic = String.valueOf(roomById.getTopic());
        message.setTopic(topic);
        System.out.println("방의 토픽 :"+ topic +"\n");

        //API 요청
        System.out.println("도착1"+"\n");
        RestTemplate http = new RestTemplate();
        System.out.println("도착2"+"\n");
        String url = "http://" + FAST_SERVER_IP + "/llm";
        ResponseMessageDto llmResponse = http.postForObject(url, message, ResponseMessageDto.class);
        System.out.println("도착3"+"\n");


        //chat 엔티티 생성
        System.out.println("도착4"+"\n");
        User relationObj = new User();
        Chat userMessage = new Chat().createChat(message.getChatRoomId(), relationObj.makeRelation(message.getSender()), message.getMessage());
        System.out.println("도착5"+"\n");
        Chat llmMessage = new Chat().createChat(message.getChatRoomId(), null, llmResponse.getMessage());
        System.out.println("도착6"+"\n");


        //메세지 저장 DB에
        System.out.println("도착7"+"\n");
        messageEmRepository.saveAll(List.of(userMessage, llmMessage));
        System.out.println("도착8"+"\n");

        llmResponse.makeMoreReadable();
        return llmResponse;
    }

}
