package com.cloudCar.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class TestLoginDemo {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception {
        String urlPath = "/v1/user/login";
        mockMvc.perform(post(urlPath)
                        .content("""
                        {"username":"刘备","password":"A0116657"}
                        """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    //获取响应状态码
                    MockHttpServletResponse response = result.getResponse();

                    //获取相应状态码
                    int status = response.getStatus();
                    System.out.println("状态码："+status);
                    //获取相应信息
                    String contentAsString = response.getContentAsString(StandardCharsets.UTF_8);
                    System.out.println("相应信息："+contentAsString);
                });
    }
}
