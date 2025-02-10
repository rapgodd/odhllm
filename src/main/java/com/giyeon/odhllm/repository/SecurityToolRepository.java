package com.giyeon.odhllm.repository;

import com.giyeon.odhllm.domain.User;
import com.giyeon.odhllm.exception.custom.WrongUserInformationException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SecurityToolRepository {

    private final EntityManager em;

    public UserDetails findByNickName(String email) {

        try{
            return em.createQuery("select u from User u where u.email = :email", User.class)
                        .setParameter("email", email)
                        .getSingleResult();
        }catch (Exception e) {
            throw new WrongUserInformationException("잘못된 이메일 또는 비밀번호입니다.");
        }

    }
}
