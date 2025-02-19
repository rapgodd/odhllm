package com.giyeon.odhllm.domain.dto;

import com.giyeon.odhllm.domain.Topic;
import lombok.Data;

@Data
public class NewRoomDto {

    private Topic topic;
    private String title;

}
