package com.giyeon.odhllm.repository;

import com.giyeon.odhllm.domain.Chat;
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

}
