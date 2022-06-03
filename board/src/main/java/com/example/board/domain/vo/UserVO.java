package com.example.board.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserVO {
    private Long userNumber;
    private String userId;
    private String userPw;
    private String userName;
    private String userRegisterDate;
}
