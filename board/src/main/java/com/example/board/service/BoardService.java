package com.example.board.service;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.FileVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    public List<FileVO> getList(Long boardBno);

    public void register(BoardVO boardVO); // 게시글 등록 메소드
    public BoardVO read(Long boardBno); // 게시글 상세보기 메소드
    public boolean modify(BoardVO boardVO); // 게시글 수정 메소드
    public boolean remove(Long bno); // 게시글 삭제 메소드
    public List<BoardVO> getList(Criteria criteria); // 게시글리스트를 받아오는 메소드, 여러개의 boardVO를 리스트에 담아 전달
    public int getTotal();
}
