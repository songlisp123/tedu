package com.ali.demo.controller;

import com.alibaba.cloud.ai.dashscope.agent.DashScopeAgent;
import com.alibaba.cloud.ai.dashscope.agent.DashScopeAgentOptions;
import com.alibaba.cloud.ai.dashscope.api.DashScopeAgentApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequestMapping("/ai")
public class aiController {

    private DashScopeAgent agent;
    @Value("${spring.ai.dashscope.agent.options.app-id}")
    private String appID;

    //构造器注入
    public aiController(DashScopeAgentApi api) {
        this.agent = new DashScopeAgent(api,
                DashScopeAgentOptions.builder()
                        .withIncrementalOutput(true) // 设置流失输出
                        .build());
    }

    // produces="text/event-stream" 指定响应类型为Server-Sent Events (SSE)流格式
    // 方法返回Flux<String>，表示这是一个流式响应,返回Flux流，每个元素都是一个响应片段
    // 表示一个异步的字符串数据流
    @GetMapping("/bailian/agent/stream")
    public Flux<String> chat(String message) {
        // 返回一个反应式流，该流由以下操作链构成：
        // 1. 调用agent的stream方法，传入一个新的Prompt对象
        // 2. 使用map操作符对流中的每个响应进行转换


        return agent.stream(
                        new Prompt(
                                message,
                                DashScopeAgentOptions.builder() //使用建造者模式创建DashScopeAgentOptions配置对象
                                        .withAppId(appID) // 设置应用ID，用于标识要调用的百炼智能体应用
                                        .build()) // 构建最终的配置对象
                )
                // 使用map操作符对agent返回的响应流中的每个元素进行转换
                .map(response -> {
                    // 从响应对象中获取结果部分，然后获取输出信息
                    // AssistantMessage通常包含AI助手的回复内容
                    AssistantMessage app_output = response.getResult().getOutput();
                    // 从助手消息中提取文本内容
                    String content = app_output.getText();
                    // 返回纯文本内容，这个内容将成为最终输出流中的元素
                    return content;
                });
    }
}
