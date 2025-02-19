package com.giyeon.odhllm.controller;

import com.giyeon.odhllm.domain.dto.ChatRoomDto;
import com.giyeon.odhllm.domain.dto.MessageDto;
import com.giyeon.odhllm.domain.dto.NewRoomDto;
import com.giyeon.odhllm.domain.dto.RoomDto;
import com.giyeon.odhllm.service.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @GetMapping("/chat-room")
    public String chat(HttpServletRequest request, Model model,
                       @RequestParam(value = "roomId", required = false) Long roomId,
                       @ModelAttribute("room") NewRoomDto newRoom){
        Long userId = (Long)request.getSession().getAttribute("user");
        ChatRoomDto chatRoom = chatService.getChatRoomList(userId, roomId);

        model.addAttribute("chatRoomList", chatRoom.getRoomList());
        model.addAttribute("messageList", chatRoom.getMessageList());
        model.addAttribute("paramRoomId", roomId);

        // MessageList에서 senderID 로그 출력
        for(MessageDto message : chatRoom.getMessageList()) {
            System.out.println("senderID: " + message.getSender());
        }

        return "responsiveChat";
    }

    @PostMapping("/chat-room")
    public String createChatRoom(HttpServletRequest request,
                                    @ModelAttribute NewRoomDto newRoom){
        Long userId = (Long)request.getSession().getAttribute("user");
        chatService.createRoom(newRoom, userId);
        return "redirect:/chat-room";
    }

}
