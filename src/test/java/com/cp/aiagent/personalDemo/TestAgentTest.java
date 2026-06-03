package com.cp.aiagent.personalDemo;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestAgentTest {
    @Resource
    private TestAgent testAgent;

    @Test
    void doChat() {
        String message = "你好,我单身，怎么找对象";
        String chatId = "123";
        String content = testAgent.doChat(message, chatId);
        Assertions.assertNotNull(content);
    }

}