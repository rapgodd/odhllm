package com.giyeon.odhllm.config;

import com.giyeon.odhllm.service.encoding.DataHashEncoder;
import com.giyeon.odhllm.service.DataEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class EncodeConfig {

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DataEncoder getDataEncoder(){
        return new DataHashEncoder(getBCryptPasswordEncoder());
    }

}
