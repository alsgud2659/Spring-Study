package com.example.board.domain.dao;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardDaoTests {
    @Autowired
    private BoardDAO boardDAO;

//    @Test
//    public void getListTest(){
//        boardDAO.getList(new Criteria()).stream().map(BoardVO::toString).forEach(log::info);
//    }

    @Test
    public void getTotalTest(){
        boardDAO.getTotal();
    }

//    @Test
//    public void insertTest(){
//        BoardVO boardVO = new BoardVO();
//        boardVO.setBoardTitle("새로운 게시글 제목3");
//        boardVO.setBoardContent("새로운 게시글 내용3");
//        boardVO.setBoardWriter("hds2222");
//
//        boardDAO.register(boardVO);
//
//        log.info("게시글 번호 : " + boardVO.getBoardBno());
//    }

//    @Test
//    public void readTest(){
//        Long boardBno = 5L;
//        log.info(boardDAO.get(boardBno).toString());
//    }

//    @Test
//    public void removeTest(){
//        Long boardBno = 4L;
//        log.info("DELETE COUNT : " + boardDAO.delete(boardBno));
//    }

//    @Test
//    public void modifyTest(){
//        Long boardBno = 3L;
//        BoardVO boardVO = boardDAO.read(boardBno);
//        if(boardVO == null) { log.info("NO BOARD"); return;}
//
//        boardVO.setBoardTitle("수정된 게시글 제목");
//        boardVO.setBoardContent("수정된 게시글 내용");
//        boardVO.setBoardWriter("한동석");
//
//        if(boardDAO.update(boardVO)) {
//              log.info("UPDATE SUCCESS");
//        } else {
//              log.info("UPDATE FAIL");
//        }
//    }

}
