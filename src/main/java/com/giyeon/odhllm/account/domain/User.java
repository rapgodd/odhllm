package com.giyeon.odhllm.account.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "users")
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String nickName;
    private String email;
    private String password;

    public void hashPassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.getPassword());
    }

    public void fillInform(SignUpDto signUpDto){
        this.nickName = signUpDto.getNickname();
        this.password = signUpDto.getPassword();
        this.email = signUpDto.getEmail();
    }




}
