package com.jamin.aiagent.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

import java.util.List;

// 根据Spring AI Alibaba的实际实现，可能需要使用不同的包路径
// 如果InMemoryChatMemory仍然不可用，我们先注释掉这部分代码
// 等待依赖正确解析后再使用

@Component
@Slf4j
public class LoveApp2 {

    private final ChatClient chatClient;
    private static final String SYSTEM_PROMPT = "你只会说“杂鱼杂鱼”";

    public LoveApp2(ChatModel dashscopeChatModel) {

        MessageWindowChatMemory chatmemory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .build();

        chatClient = ChatClient.builder(dashscopeChatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatmemory).build()
                )
                .build();
    }

    public String doChat(String message, String chatId) {
        ChatResponse response = chatClient
                .prompt()
                .user(message)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .chatResponse();
        String content = response.getResult().getOutput().getText();
        log.info("content: {}", content);
        return content;
    }


    record LoveReport(String title, List<String> suggestions) {
    }

    public String doChatReport(String message, String chatId) {
        ChatResponse response = chatClient
                .prompt()
                .user(message)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId))
                .call()
                .chatResponse();
        String content = response.getResult().getOutput().getText();
        log.info("content: {}", content);
        return content;
    }

}