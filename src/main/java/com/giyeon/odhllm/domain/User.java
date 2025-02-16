package com.giyeon.odhllm.domain;

import com.giyeon.odhllm.domain.dto.SignUpDto;
import com.giyeon.odhllm.exception.custom.EmptyUserInformException;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

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

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private Role userRole;

    public void hashPassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(this.getPassword());
    }

    public void fillInform(SignUpDto signUpDto){

        if(checkNull(signUpDto)==null){
            this.nickName = signUpDto.getNickname();
            this.password = signUpDto.getPassword();
            this.email = signUpDto.getEmail();
            this.userRole = Role.ROLE_UNSUBSCRIBED;
        }else{
            throw new EmptyUserInformException(checkNull(signUpDto)+" 공란은 허용이 되지 않습니다.");
        }

    }

    /**
     * ==Deprecated==
     * 세션인증으로 구조 변경으로 인해
     * 사용되지 않는 메소드
     */
    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }


    public String checkNull(SignUpDto signUpDto){
        if(signUpDto.getNickname() == null){
            return "닉네임";
        }else if(signUpDto.getPassword() == null){
            return "비밀번호";
        }else if(signUpDto.getEmail() == null){
            return "이메일";
        }else{
            return null;
        }
    }




}
