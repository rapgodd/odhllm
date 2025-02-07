package com.giyeon.odhllm.domain;

import com.giyeon.odhllm.domain.account.domain.User;

public interface DataEncoder {

    void encodePwd(User user);

}
