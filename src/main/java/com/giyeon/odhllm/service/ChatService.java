package com.giyeon.odhllm.service;

import com.giyeon.odhllm.domain.Chat;
import com.giyeon.odhllm.domain.ChatRoom;
import com.giyeon.odhllm.domain.User;
import com.giyeon.odhllm.domain.dto.ChatRoomDto;
import com.giyeon.odhllm.domain.dto.MessageDto;
import com.giyeon.odhllm.domain.dto.NewRoomDto;
import com.giyeon.odhllm.domain.dto.RoomDto;
import com.giyeon.odhllm.exception.custom.WrongUserInformationException;
import com.giyeon.odhllm.repository.ChatRoomRepository;
import com.giyeon.odhllm.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ChatService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;


    @Transactional(readOnly = true)
    public ChatRoomDto getChatRoomList(Long userId, Long roomId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

        List<ChatRoom> userRooms = chatRoomRepository.findAllRoomsByUser(user);
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

            List<Chat> messages = chatRoomRepository.getMessagesByRoomId(roomId);
            messages.forEach(message -> {
                chatRoomDto.getMessageList().add(new MessageDto(message.makeMoreReadable().getMessage(),
                        message.getSender()==null?null:message.getSender().getId()));
            });

            return chatRoomDto;
        }
    }

    @Transactional
    public void createRoom(NewRoomDto newRoom, Long userId) {
        //유저 객체 가져오기
          User user = userRepository.findById(userId)
                      .orElseThrow(() ->
                      new WrongUserInformationException("세션이 만료되었거나 존재하지 않습니다. 다시 로그인해 주세요."));

          ChatRoom room = new ChatRoom();
          room.fillEmptyRoom(user, newRoom);
          chatRoomRepository.saveRoom(room);
    }
}
