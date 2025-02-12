package com.giyeon.odhllm.repository;

import com.giyeon.odhllm.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public Optional<User> areTokensEqual(String refreshToken) {
        return em.createQuery("select u from User u where u.refreshToken = :refreshToken", User.class)
                .setParameter("refreshToken", refreshToken).
                getResultList().stream().findAny();
    }

    public Optional<User> findByEmail(String email) {
        return em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList().stream().findAny();
    }

}
