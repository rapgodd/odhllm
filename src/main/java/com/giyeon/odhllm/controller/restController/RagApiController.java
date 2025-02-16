package com.giyeon.odhllm.controller.restController;

import com.giyeon.odhllm.controller.Tcp;
import com.giyeon.odhllm.domain.dto.MessageDto;
import com.giyeon.odhllm.domain.dto.ResponseDto;
import com.giyeon.odhllm.domain.dto.ResponseMessageDto;
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
    public ResponseEntity<ResponseDto<?>> sendMessage(@RequestBody MessageDto messageDto) {

        ResponseMessageDto responseMessageDto = tcp.send(messageDto);

        return null;
    }

}
