package com.giyeon.odhllm.controller;

import com.giyeon.odhllm.domain.dto.ChatRoomDto;
import com.giyeon.odhllm.service.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat-room")
    public String chat(HttpServletRequest request, Model model,
                       @RequestParam(value = "roomId", required = false) Long roomId){
        Long userId = (Long)request.getSession().getAttribute("user");
        ChatRoomDto chatRoom = chatService.getChatRoomList(userId, roomId);

        model.addAttribute("chatRoomList", chatRoom.getRoomList());
        model.addAttribute("messageList", chatRoom.getMessageList());
        return "chat";
    }

}
