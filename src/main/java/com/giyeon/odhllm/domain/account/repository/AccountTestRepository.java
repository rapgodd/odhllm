package com.giyeon.odhllm.domain.account.repository;

import com.giyeon.odhllm.domain.account.domain.User;
import com.giyeon.odhllm.domain.account.repository.Interface.AccountManaging;

public class AccountTestRepository implements AccountManaging {

    @Override
    public void create(User user) {
        String password = user.getPassword();
        System.out.println(password);
    }

}
