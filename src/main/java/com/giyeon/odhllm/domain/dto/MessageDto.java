package com.giyeon.odhllm.domain.dto;

import lombok.Data;


@Data
public class MessageDto {

    private String message;
    private Long sender;
    private String topic;
    private Long chatRoomId;

    public MessageDto(String message, Long sender) {
        this.message = message;
        if(sender == null){
            this.sender = 0L;
        }
    }


}
