package com.giyeon.odhllm.domain;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @Lob
    private String message;


    public Chat createChat(Long chatRoomId, User sender, String message){
        Chat chat = new Chat();
        chat.chatRoomId = chatRoomId;
        chat.sender = sender;
        chat.message = message;
        return chat;
    }

    public Chat makeMoreReadable() {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(this.message);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        this.message = renderer.render(document);
        return this;
    }

    public static Chat of(Long chatRoomId, User user, String message) {
        Chat chat = new Chat();
        chat.chatRoomId = chatRoomId;
        chat.sender = user;
        chat.message = message;
        return chat;
    }


}
