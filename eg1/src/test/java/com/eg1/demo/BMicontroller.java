package com.eg1.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(com.eg1.demo.controller.BMIcontroller.class)
public class BMicontroller {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testBmiController() throws Exception {
        String Path = "/bmi/1.75/150/";
        ResultActions MvcResult = mockMvc.perform(get(Path))
                .andExpect(status().isOk());
        //创建一个url对象
        String urlPath = "http://localhost:8080"+Path;
        URL url = new URI(urlPath).toURL();
        URLConnection connection = url.openConnection();
        connection.connect();
        //返回请求的属性

        // print header fields

        Map<String, List<String>> headers = connection.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : headers.entrySet())
        {
            String key = entry.getKey();
            for (String value : entry.getValue())
                System.out.println(key + ": " + value);
        }

        // print convenience functions

        System.out.println("----------");
        System.out.println("getContentType: " + connection.getContentType());
        System.out.println("getContentLength: " + connection.getContentLength());
        System.out.println("getContentEncoding: " + connection.getContentEncoding());
        System.out.println("getDate: " + connection.getDate());
        System.out.println("getExpiration: " + connection.getExpiration());
        System.out.println("getLastModifed: " + connection.getLastModified());
        System.out.println("----------");

        String encoding = connection.getContentEncoding();
        if (encoding == null) encoding = "UTF-8";
        try (var in = new Scanner(connection.getInputStream(), encoding))
        {
            // print first ten lines of contents

            for (int n = 1; in.hasNextLine() && n <= 10; n++)
                System.out.println(in.nextLine());
            if (in.hasNextLine()) System.out.println(". . .");
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
