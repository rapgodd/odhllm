package com.giyeon.odhllm.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class ChatRoomDto {
    private List<RoomDto> roomList;
    private List<MessageDto> messageList;
}
