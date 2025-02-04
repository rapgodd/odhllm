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


    private static DataEncoder dataEncoder = user -> {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.hashPassword(bCryptPasswordEncoder);
    };

}