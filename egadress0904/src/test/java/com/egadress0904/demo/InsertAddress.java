package com.egadress0904.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InsertAddress {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        String path = "/v1/address/add/";
        mockMvc.perform(post(path)
                        .content("""
                                {
                                "receiver":"赵云",
                                "address":"西蜀子云亭",
                                "email":"197871978@qq.com",
                                "mobile":"19878133652",
                                "tag":"发送中……"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    MockHttpServletResponse mockHttpServletResponse = result.getResponse();

                    //获取状态吗
                    int status = mockHttpServletResponse.getStatus();
                    System.out.println("状态吗："+status);

                    //获取请求体
                    String message = mockHttpServletResponse.getContentAsString(StandardCharsets.UTF_8);
                    System.out.println("message："+message);
                });
    }
}
