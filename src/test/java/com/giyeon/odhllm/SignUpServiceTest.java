package com.giyeon.odhllm;

import com.giyeon.odhllm.account.domain.SignUpDto;
import com.giyeon.odhllm.account.domain.User;
import com.giyeon.odhllm.account.service.DataEncoder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class SignUpServiceTest {

    @Test
    void notSamePwdTest() {

        //when
        SignUpDto userInform = new SignUpDto("giyeon", "test@email.com", "testpwd");
        User user = new User();
        user.fillInform(userInform);
        dataEncoder.encodePwd(user);

        //then
        Assertions.assertThat(user.getPassword()).isNotEqualTo("testpwd");

    }

    @Test
    void notFillNicknameErrorTest() {

        //given
        SignUpDto userInform = new SignUpDto(null, "test", "test");
        User user = new User();


        //then
        try{
            user.fillInform(userInform);
        }catch (Exception e){
            Assertions.assertThat(e.getMessage()).isEqualTo("닉네임 공란은 허용이 되지 않습니다.");
        }

    }

    @Test
    void notFillEmailErrorTest() {

        //given
        SignUpDto userInform = new SignUpDto("test", null, "test");
        User user = new User();


        //then
        try{
            user.fillInform(userInform);
        }catch (Exception e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이메일 공란은 허용이 되지 않습니다.");
        }

    }

    @Test
    void notFillPasswordErrorTest() {

        //given
        SignUpDto userInform = new SignUpDto("test", "test", null);
        User user = new User();


        //then
        try{
            user.fillInform(userInform);
        }catch (Exception e){
            Assertions.assertThat(e.getMessage()).isEqualTo("비밀번호 공란은 허용이 되지 않습니다.");
        }

    }


    private static DataEncoder dataEncoder = user -> {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.hashPassword(bCryptPasswordEncoder);
    };

}