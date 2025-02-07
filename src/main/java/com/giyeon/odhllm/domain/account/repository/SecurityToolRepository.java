package com.giyeon.odhllm.domain.account.repository;

import com.giyeon.odhllm.domain.account.domain.User;
import com.giyeon.odhllm.domain.account.exception.custom.WrongUserInformationException;
import com.giyeon.odhllm.domain.account.repository.Interface.AuthTool;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SecurityToolRepository implements AuthTool {

    private final EntityManager em;

    @Override
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
