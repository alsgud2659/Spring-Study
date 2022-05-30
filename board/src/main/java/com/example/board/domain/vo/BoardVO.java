package com.example.board.domain.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component // component로 spring이 해당 클래스를 관리할 수 있게 등록
@Data // getter, setter, toString 재정의 등등을 해줌
public class BoardVO {
    private Long boardBno; // 게시글 번호
    private String boardTitle; // 게시글 제목
    private String boardContent; // 게시글 내용
    private String boardWriter; // 게시글 작성자
    private String boardRegisterDate; // 게시글 등록 일자
    private String boardUpdateDate; // 게시글 수정 일자
}
