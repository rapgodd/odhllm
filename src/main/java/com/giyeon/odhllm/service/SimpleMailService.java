package com.giyeon.odhllm.service;

import com.giyeon.odhllm.domain.dto.MailVerificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SimpleMailService implements EmailSender {

    @Value("${spring.mail.username}")
    private String sender;
    private final JavaMailSender javaMailSender;
    private final RedisTemplate<String, Object> redisTemplate;



    @Override
    public String send(MailVerificationDto details) {

        String code = createCode();

        SimpleMailMessage mailMessage
                = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(details.getEmail());
        mailMessage.setText(code);
        mailMessage.setSubject("[인증] OdhLLM 이메일 인증번호 전송");
        javaMailSender.send(mailMessage);

        redisTemplate.opsForValue().set(details.getEmail(), code);
        return "인증번호가 전송되었습니다. 확인 후 입력해주세요";
    }

    private String createCode() {
        int length = 6;
        try {
            Random code = SecureRandom.getInstanceStrong();
            StringBuilder wrapper = new StringBuilder();
            for (int i = 0; i < length; i++) {
                wrapper.append(code.nextInt(10));
            }
            return wrapper.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }



}
