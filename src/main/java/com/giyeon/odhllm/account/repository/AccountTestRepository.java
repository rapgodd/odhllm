package com.giyeon.odhllm.account.repository;

import com.giyeon.odhllm.account.domain.User;
import com.giyeon.odhllm.account.service.AccountManaging;

public class AccountTestRepository implements AccountManaging {

    @Override
    public void save(User user) {
        String password = user.getPassword();
        System.out.println(password);
    }

}
