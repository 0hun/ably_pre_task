package com.ably.pre_task;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootTest
class PreTaskApplicationTests {

    @Test
    void contextLoads() {
    }

}
