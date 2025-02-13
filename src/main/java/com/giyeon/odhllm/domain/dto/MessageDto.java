package com.giyeon.odhllm.domain.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MessageDto {

    private String message;
    private Long sender;
    private String topic;
    private Long chatRoomId;

//    public MessageDto(String message, Long sender, String topic) {
//        this.message = message;
//        this.sender = sender;
//        this.topic = topic;
//    }

}
