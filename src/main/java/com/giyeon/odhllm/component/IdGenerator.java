package com.giyeon.odhllm.component;

import com.giyeon.odhllm.util.Snowflake;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

    Snowflake snowflake = new Snowflake(1,1000000000000L);

    public long generate(){
        return snowflake.nextId();
    }

}
