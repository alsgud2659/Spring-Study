package com.example.board.mapper;

import com.example.board.domain.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

//    @Test
//    public void replyMapperTest() {
//        log.info(userMapper+"");
//    }

//    @Test
//    public void userInsertTest() {
//        UserVO userVO = new UserVO();
//        userVO.setUserId("abcd1234");
//        userVO.setUserPw("1234");
//        userVO.setUserName("홍길동");
//        userMapper.insert(userVO);
//    }

//    @Test
//    public void getUser() {
//        // 가져올 댓글 번호
//        Long userNumber = 1L;
//        // 결과 출력
//        log.info("유저 정보 : " + userMapper.getUser(userNumber).toString());
//    }

//    @Test
//    public void deleteTest(){
//        Long userNumber = 2L;
//        log.info("DELETE COUNT : " + userMapper.delete(userNumber));
//    }

    @Test
    public void updateTest(){
        Long userNumber = 1L;
        UserVO userVO = userMapper.getUser(userNumber);
        if(userVO == null) { log.info("NO USER"); return;}

        userVO.setUserPw("1122");
        userVO.setUserName("이민형");

        log.info("UPDATE COUNT : " + userMapper.update(userVO));
    }
}
