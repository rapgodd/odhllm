package com.giyeon.odhllm.repository;

import com.giyeon.odhllm.repository.Interface.Creation;
import com.giyeon.odhllm.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CreationRepository implements Creation {

    private final EntityManager em;
    private final TransactionTemplate tcTemplate;

    @Override
    public void create(User account){
        tcTemplate.execute(status -> {
            em.persist(account);
            return null;
        });
    }

}
