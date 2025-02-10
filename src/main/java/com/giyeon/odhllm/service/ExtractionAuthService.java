package com.giyeon.odhllm.service;

import com.giyeon.odhllm.repository.SecurityToolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExtractionAuthService implements UserDetailsService {

    private final SecurityToolRepository authTool;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         return authTool.findByNickName(email);
    }


}
