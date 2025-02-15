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

    /**
     * ==Deprecated==
     *
     * 토큰 인증 방식(Stateless)에서
     * 세션 인증 방식으로
     * 프로젝트 변경으로 사용되지 않음
     */
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
