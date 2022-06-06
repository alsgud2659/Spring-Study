package com.example.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Slf4j
public class UserControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    //    서버 환경 및 설정, 요청 등을 처리해주는 WebApplicationContext를 불러온다.
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void modifyTest() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/user/modify/21")
                .param("userId", "mtest2")
                .param("userPw", "mtestpw1")
                .param("userName", "수정된 이름")
        ).andReturn().getFlashMap().toString());
    }
}
