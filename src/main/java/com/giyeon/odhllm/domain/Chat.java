package com.giyeon.odhllm.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;


@Entity
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long chatRoomId;

    @Nullable
    private Long sender;

    @Lob
    private String message;


    public Chat createChat(Long chatRoomId, Long sender, String message){
        Chat chat = new Chat();
        chat.chatRoomId = chatRoomId;
        chat.sender = sender;
        chat.message = message;
        return chat;
    }


}
