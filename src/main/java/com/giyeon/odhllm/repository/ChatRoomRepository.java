package com.giyeon.odhllm.repository;

import com.giyeon.odhllm.domain.Chat;
import com.giyeon.odhllm.domain.ChatRoom;
import com.giyeon.odhllm.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRoomRepository {

    private final EntityManager em;

    public List<ChatRoom> findAllRoomsByUser(User user) {
        return em.createQuery("select c from ChatRoom c where c.user = :user", ChatRoom.class)
                .setParameter("user", user)
                .getResultList();
    }

    public List<Chat> getMessagesByRoomId(Long roomId) {
        return em.createQuery("select c from Chat c left join fetch c.sender where c.chatRoomId = :roomId", Chat.class)
                .setParameter("roomId", roomId)
                .getResultList();
    }

    public void saveRoom(ChatRoom chatRoom){
        em.persist(chatRoom);
    }

    public ChatRoom findRoomById(Long roomId){
        return em.find(ChatRoom.class, roomId);
    }

}
