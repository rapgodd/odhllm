package com.giyeon.odhllm.repository;

import com.giyeon.odhllm.domain.Chat;
import com.giyeon.odhllm.domain.ChatRoom;
import com.giyeon.odhllm.domain.User;
import com.giyeon.odhllm.service.Message;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessageEmRepository implements Message {

    private final EntityManager em;


    @Override
    public boolean saveAll(List<Chat> chat) {
        for (Chat c : chat) {
            em.persist(c);
        }
        return true;
    }


    public List<ChatRoom> findAllRoomsByUser(User user) {
        return em.createQuery("select c from ChatRoom c where c.user = :user", ChatRoom.class)
                    .setParameter("user", user)
                    .getResultList();
    }

    public List<Chat> getMessagesByRoomId(Long roomId) {
        return em.createQuery("select c from Chat c where c.chatRoomId = :roomId", Chat.class)
                    .setParameter("roomId", roomId)
                    .getResultList();
    }
}
