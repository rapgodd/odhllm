package com.giyeon.odhllm.service;

import com.giyeon.odhllm.domain.Chat;
import com.giyeon.odhllm.domain.ChatRoom;
import com.giyeon.odhllm.domain.User;
import com.giyeon.odhllm.domain.dto.ChatRoomDto;
import com.giyeon.odhllm.domain.dto.MessageDto;
import com.giyeon.odhllm.domain.dto.RoomDto;
import com.giyeon.odhllm.repository.MessageEmRepository;
import com.giyeon.odhllm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ChatService {

    private final MessageEmRepository chatRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public ChatRoomDto getChatRoomList(Long userId, Long roomId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        List<ChatRoom> userRooms = chatRepository.findAllRoomsByUser(user);
        ChatRoomDto chatRoomDto = new ChatRoomDto(new ArrayList<>(), new ArrayList<>());

        if(roomId==null){
            userRooms.forEach(chatRoom -> {
                        chatRoomDto.getRoomList().add(new RoomDto(chatRoom.getTitle(), chatRoom.getId()));
            });
            return chatRoomDto;
        }else {

            userRooms.forEach(chatRoom -> {
                chatRoomDto.getRoomList().add(new RoomDto(chatRoom.getTitle(), chatRoom.getId()));
            });

            List<Chat> messages = chatRepository.getMessagesByRoomId(roomId);
            messages.forEach(message -> {
                chatRoomDto.getMessageList().add(new MessageDto(message.getMessage(), message.getSender()));
            });

            return chatRoomDto;
        }
    }

}
