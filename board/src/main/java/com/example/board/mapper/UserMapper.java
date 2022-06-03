package com.example.board.mapper;

import com.example.board.domain.vo.UserVO;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 회원가입
    public void insert(UserVO userVO);

    // 유저 정보 가져오기
    public UserVO getUser(Long userNumber);

    // 회원탈퇴
    public int delete(Long userNumber);

    // 회원정보 수정
    public int update(UserVO userVO);
}
