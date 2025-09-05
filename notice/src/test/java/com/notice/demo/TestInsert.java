package com.notice.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TestInsert {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        String path = "/v1/notice/add";
        mockMvc.perform(post(path)
                .param("title","懒洋洋")
                .param("content","小兰")
                .param("type","1")
                .param("status","2"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    MockHttpServletResponse mockHttpServletResponse = result.getResponse();

                    //获取状态吗
                    int status = mockHttpServletResponse.getStatus();
                    System.out.println("状态吗："+status);

                    //获取返回给客户端的信息
                    String message = mockHttpServletResponse.getContentAsString(StandardCharsets.UTF_8);
                    System.out.println("message："+message);

                    byte[] contentAsByteArray = mockHttpServletResponse.getContentAsByteArray();

                });
    }
}
