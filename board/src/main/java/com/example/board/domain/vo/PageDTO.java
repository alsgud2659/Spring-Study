package com.example.board.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
@NoArgsConstructor
public class PageDTO {
    private Criteria criteria; // criteria 객체를 생성

    private int startPage; // 시작 페이지
    private int endPage; // 끝페이지
    private int realEnd; // 게시글 페이지의 마지막
    private int pageCount; // 몇페이지를 찍어주는지?
    private boolean prev, next; // 이전과 다음


    private int total; // 총 게시글 개수

    public PageDTO(Criteria criteria, int total) {
        this(criteria, 10, total);
    }

    public PageDTO(Criteria criteria, int pageCount, int total) {
        this.criteria = criteria;
        this.total = total; // 총 게시글 수
        // 현재 페이지를 기준으로 소수점으로 계산하여 보여질 마지막 페이지 번호를 구한다.
        this.endPage = (int)Math.ceil(criteria.getPageNum() / (double)pageCount) * pageCount;
        this.startPage = this.endPage - pageCount + 1; // 시작페이지는 마지막 페이지에서 pageCount를 뺀 후 1을 더한 값
        // ex) 21 ~ 30 페이지를 띄운다면 21은 마지막 페이지인 30에서 10을 뺀 후 1을 더해서 만들어짐
        this.realEnd = (int)Math.ceil((double)total / criteria.getAmount());
        // 진짜 마지막 페이지는 총 게시글 수를 amount로 나눈 뒤 올림하면 됨
        // 505개의 게시글이 있다면 505를 10으로 나눈 뒤 55.5를 올림해서 56페이지가 realEnd가 됨

        if (realEnd < this.endPage){ // 진짜 마지막 페이지가 마지막페이지보다 작으면 마지막페이지가 진짜 마지막페이지가 된다
            this.endPage = realEnd;
        }

        this.prev = this.startPage > 1; // 현재 페이지가 1보다 크다면 이전 버튼을 만듬
        this.next = this.endPage < realEnd; // 현재 페이지가 맨 마지막 페이지라면 다음버튼을 없앰

    }
}
