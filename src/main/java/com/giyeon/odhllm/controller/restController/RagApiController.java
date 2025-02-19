package com.giyeon.odhllm.controller.restController;

import com.giyeon.odhllm.controller.Tcp;
import com.giyeon.odhllm.domain.dto.MessageDto;
import com.giyeon.odhllm.domain.dto.ResponseDto;
import com.giyeon.odhllm.domain.dto.ResponseMessageDto;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RagApiController {

    private final Tcp tcp;

    @PostMapping("/message/v1")
    public ResponseEntity<ResponseDto<?>> sendMessage(@RequestBody MessageDto messageDto, HttpServletRequest request) {
        Long userId = (Long)request.getSession().getAttribute("user");
        messageDto.setSender(userId);
        messageDto.setTopic("Spring");
        ResponseMessageDto responseMessageDto = tcp.send(messageDto);
        return ResponseDto.ok(responseMessageDto);
    }

}
