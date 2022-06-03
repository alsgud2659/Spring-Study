package com.example.board.domain.dao;


import com.example.board.domain.vo.ReplyVO;
import com.example.board.domain.vo.UserVO;
import com.example.board.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserDAO {
    private final UserMapper userMapper;
    // 회원가입
    public void register(UserVO userVO){
        log.info("register..... : " + userVO);
        userMapper.insert(userVO);
    }

    // 유저 정보 가져오기
    public UserVO read(Long userNumber){
        log.info("read ..... : " + userNumber);
        return userMapper.getUser(userNumber);
    }

    //댓글 삭제
    public boolean remove(Long userNumber){
        log.info("delete ..... : " + userNumber);
        return userMapper.delete(userNumber) == 1;
    }

    //댓글 수정
    public boolean modify(UserVO userVO){
        log.info("update ..... : " + userVO);
        return userMapper.update(userVO) == 1;
    }
}
