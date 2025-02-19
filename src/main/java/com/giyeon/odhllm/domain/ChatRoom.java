package com.giyeon.odhllm.domain;

import com.giyeon.odhllm.domain.dto.NewRoomDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    @Enumerated(EnumType.STRING)
    private Topic topic;

    public void fillEmptyRoom(User user, NewRoomDto newRoomDto){
        this.user = user;
        this.title = newRoomDto.getTitle();
        this.topic = newRoomDto.getTopic();
    }

}