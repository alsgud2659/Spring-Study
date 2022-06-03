package com.example.board.service;

import com.example.board.domain.dao.UserDAO;
import com.example.board.domain.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserDAO userDAO;

    //회원 가입
    public void register(UserVO userVO){
        log.info("register..... : " + userVO);
        userDAO.register(userVO);
    }

    //유저 정보 조회
    public UserVO read(Long userNumber){
        log.info("read...... : " + userNumber);
        return userDAO.read(userNumber);
    }

    //회원 탈퇴
    public boolean remove(Long userNumber){
        log.info("remove...... : " + userNumber);
        return userDAO.remove(userNumber);
    }

    //회원 정보 수정
    public boolean modify(UserVO userVO){
        log.info("modify........ : " + userVO);
        return userDAO.modify(userVO);
    }
}
