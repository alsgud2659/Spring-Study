package com.example.board.domain.vo;
//Criteria : 검색의 기준

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@Data
@AllArgsConstructor
public class Criteria {
    private int pageNum; // 페이지 번호
    private int amount; // 한 페이지에 몇개의 게시글을 출력할지 정해주는 변수
    private String type;
    private String keyword;

    public Criteria() {
        this(1, 10); // 기본생성자 호출시 페이지번호는 1, 한페이지에 뿌려지는 게시글 개수는 10개
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    // 쿼리스트링을 자동으로 만들어주는 메소드
    public String getListLink(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum", this.pageNum)
                .queryParam("amount", this.amount)
                .queryParam("type", this.type)
                .queryParam("keyword", this.keyword);

        return builder.toUriString();
    }

    public String[] getTypes() {
        return type == null ? new String[] {} : type.split("");
    }
}
