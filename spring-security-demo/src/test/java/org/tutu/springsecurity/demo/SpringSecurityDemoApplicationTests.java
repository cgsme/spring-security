package org.tutu.springsecurity.demo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecurityDemoApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    // 前
    @Before
    public void setup() {
        // 获取mockMvc对象
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void whenQuerySuccess() throws Exception {
        String content = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("username", "tutu")   // 添加请求参数
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())  // 添加期望结果
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                .andReturn().getResponse().getContentAsString();// 返回结果中的json长度为3，$.length()代表期望返回集合，集合长度为3
        System.out.println(content);
    }

    @Test
    public void whenGetInfoSuccess() throws Exception {

        String content = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(content);
    }

    @Test
    public void whenGetInfoFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/d")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

    @Test
    public void whenCreateSuccess() throws Exception {
        Date date = new Date();
        System.out.println(date.getTime());
        String content = "{\"userName\":\"tom\",\"password\":null, \"birthday\": \"" + date.getTime() + "\"}";
        String content1 = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(content1);
    }

    @Test
    public void whenUpdateSuccess() throws Exception {
        // 一年后的时间
        Date date = new Date(LocalDateTime.now()
                .plusYears(1)   // 一年后
                .atZone(ZoneId.systemDefault())// 系统默认市区
                .toInstant()
                .toEpochMilli());  // 到毫秒
        System.out.println(date.getTime());
        String content = "{\"id\":\"1\",\"userName\":\"tom\",\"password\":null, \"birthday\": \"" + date.getTime() + "\"}";
        String content1 = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(content1);
    }
}

