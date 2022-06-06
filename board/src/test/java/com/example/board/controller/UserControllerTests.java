package com.example.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@Slf4j
public class UserControllerTests {
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    //    @Test
//    public void modifyTest() throws Exception {
//        log.info(mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
//                .param("boardTitle", "수정된 새 글 제목")
//                .param("boardContent", "수정된 새 글 내용")
//                .param("boardWriter", "수정된 작성자")
//                .param("boardBno", "6")
//        ).andReturn().getFlashMap().toString());
//    }
}
