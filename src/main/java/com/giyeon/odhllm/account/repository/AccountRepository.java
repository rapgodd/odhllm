package com.giyeon.odhllm.account.repository;

import com.giyeon.odhllm.account.domain.User;
import com.giyeon.odhllm.account.service.AccountManaging;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

@Repository
@RequiredArgsConstructor
public class AccountRepository implements AccountManaging {

    private final EntityManager em;
    private final TransactionTemplate tmTemplate;


    public void save(User account){
        tmTemplate.execute(status -> {
            em.persist(account);
            return null;
        });
    }


}
