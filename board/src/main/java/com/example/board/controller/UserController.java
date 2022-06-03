package com.example.board.controller;

import com.example.board.domain.vo.UserVO;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/user/*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 회원 가입
    @PostMapping("/join")
    public String register(@RequestBody UserVO userVO){
        userService.register(userVO);
        return "회원가입성공";
    }

    // 유저 정보 조회
    @GetMapping("/get/{uno}")
    public UserVO read(@PathVariable("uno") Long userNumber){
        return userService.read(userNumber);
    }

    // 유저 정보 수정
    @PostMapping("/modify/{uno}")
    public String modify(@PathVariable("uno") Long userNumber, @RequestBody UserVO userVO){
        userVO.setUserNumber(userNumber);
        userService.modify(userVO);
        return "유저 정보 수정 완료";
    }

    // 회원 탈퇴
    @GetMapping("/remove/{uno}")
    public String remove(@PathVariable("uno") Long userNumber){
        userService.remove(userNumber);
        return "회원 탈퇴 완료";
    }

}
