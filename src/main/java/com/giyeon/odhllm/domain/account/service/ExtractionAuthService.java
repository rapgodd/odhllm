package com.giyeon.odhllm.domain.account.service;

import com.giyeon.odhllm.domain.account.repository.Interface.AuthTool;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExtractionAuthService implements UserDetailsService {

    private final AuthTool authTool;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         return authTool.findByNickName(email);
    }


}
