package com.ivos.demo.geofenceTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GeoQUeryTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    void test() throws Exception {
        String url = "/v1/geofence/query";
        mockMvc.perform(get(url)
//                        .queryParam("name","化")
                        .queryParam("status","0")
//                                .queryParam("id","103")
                )
                .andExpect(status().isOk())
                .andExpect(result -> {
                    //获取请求体
                    MockHttpServletRequest mockHttpServletRequest = result.getRequest();
                    Map<String, String[]> map = mockHttpServletRequest.getParameterMap();

                    //获取请求方法
                    String method = mockHttpServletRequest.getMethod();
                    System.out.println("请求方法："+method);

                    //获取请求代理
                    String protocol = mockHttpServletRequest.getProtocol();
                    System.out.println("请求协议："+protocol);

                    //获取请求路径
                    String pathInfo = mockHttpServletRequest.getPathInfo();
                    System.out.println("请求资源路径："+pathInfo);

                    //请求体
                    for(Map.Entry<String,String[]> entry : map.entrySet()) {
                        String key  = entry.getKey();
                        String[] values = entry.getValue();
                        Arrays.stream(values).forEach(r->{
                            System.out.println("请求参数键:"+key+"\t"+"参数值:"+r);
                        });
                    }
                    MockHttpServletResponse mockHttpServletResponse = result.getResponse();

                    //获取状态吗
                    int status = mockHttpServletResponse.getStatus();
                    System.out.println("状态吗："+status);

                    //获取请求体的数据格式
                    String contentType = mockHttpServletResponse.getContentType();
                    System.out.println("返回数据类型："+contentType);

                    //获取请求体
                    String message = mockHttpServletResponse.getContentAsString(StandardCharsets.UTF_8);
                    System.out.println("message："+message);

                });
    }
}
