package com.example.board.domain.dao;

import com.example.board.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class UserDaoTests {
    @Autowired
    private UserDAO userDAO;

    private Long[] arBno = {14L, 15L, 16L, 17L, 18L};

//    @Test
//    public void Test() {
//        log.info(userDAO+"");
//    }

//    @Test
//    public void replyInsertTest() {
//        IntStream.rangeClosed(11,20).forEach(i -> {
//            UserVO userVO = new UserVO();
//            userVO.setUserId("test" + i);
//            userVO.setUserPw("testpw" + i);
//            userVO.setUserName("테스트유저" + i);
//            userDAO.register(userVO);
//        });
//    }
    
    @Test
    public void modifyTest() {
        UserVO userVO = new UserVO();
        userVO.setUserNumber(6L);
        userVO.setUserPw("modifiedpw1");
        userVO.setUserName("수정된테스트유저1");

        userDAO.modify(userVO);
    }

//    @Test
//    public void getUserTest() {
//        Long userNumber = 1L;
//        log.info("유저 정보 : " + userDAO.read(userNumber).toString());
//    }

//    @Test
//    public void removeTest() {
//        Long userNumber = 5L;
//        log.info("삭제된 유저 : " + userDAO.remove(userNumber));
//    }
}
