package com.jamin.aiagent.app;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LoveApp2Test {

    @Resource
    private LoveApp2 loveApp2;


    @Test
    void testChat2() {
        String chatId = UUID.randomUUID().toString();

        String message = "你好，我是花花";
        String answer = loveApp2.doChat(message, chatId);
        Assertions.assertNotNull(answer);

        message = "你叫什么？";
        answer = loveApp2.doChat(message, chatId);
        Assertions.assertNotNull(answer);
    }
}