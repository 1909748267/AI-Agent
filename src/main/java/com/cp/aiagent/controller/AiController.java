package com.cp.aiagent.controller;

import com.cp.aiagent.agent.Manus;
import com.cp.aiagent.app.LoveAPP;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
@RequestMapping("/ai")
public class AiController {
    @Resource
    private LoveAPP loveAPP;

    @Resource
    private ToolCallback[] allTools;

    @Resource
    private ChatModel dashscopeChatModel;

    /**
     * 同步调用方法，等待AI结果然后一次性返回
     * @param message 用户输入的文本
     * @param chatId 会话记忆id
     * @return
     */
    @GetMapping("/love_app/chat/sync")
    public String doChatWithLoveAppSync(String message,String chatId) {
        return loveAPP.doChat(message,chatId);
    }

    @GetMapping(value = "/love_app/chat/see",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> doChatWithLoveAppSEE(String message,String chatId) {
        return loveAPP.doChatByStream(message,chatId);
    }

    @GetMapping(value = "/love_app/chat/sse")
    public Flux<ServerSentEvent<String>> doChatWithLoveAppSSE(String message, String chatId) {
        return loveAPP.doChatByStream(message, chatId)
                .map(chunk -> ServerSentEvent.<String>builder()
                        .data(chunk)
                        .build());
    }

    @GetMapping(value = "/love_app/chat/sse/emitter")
    public SseEmitter doChatWithLoveAppSseEmitter(String message,String chatId) {
        SseEmitter emitter = new SseEmitter(180000L);
        loveAPP.doChatByStream(message,chatId).subscribe(
                chunk->{
                    try {
                        emitter.send(chunk);
                    } catch (IOException e) {
                        emitter.completeWithError(e);
                    }
                },emitter::completeWithError,emitter::complete
        );
        return emitter;
    }



    /**
     * 流式调用 Manus 超级智能体
     *
     * @param message
     * @return
     */
    @GetMapping("/manus/chat")
    public SseEmitter doChatWithManus(String message) {
        Manus manus = new Manus(allTools, dashscopeChatModel);
        return manus.runStream(message);
    }

}

