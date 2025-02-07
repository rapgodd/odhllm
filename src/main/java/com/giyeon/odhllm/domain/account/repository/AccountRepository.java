package com.giyeon.odhllm.domain.account.repository;

import com.giyeon.odhllm.domain.account.repository.Interface.AccountManaging;
import com.giyeon.odhllm.domain.account.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
@RequiredArgsConstructor
public class AccountRepository implements AccountManaging {

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
