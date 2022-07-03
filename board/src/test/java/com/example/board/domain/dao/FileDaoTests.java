package com.example.board.domain.dao;

import com.example.board.domain.vo.FileVO;
import com.example.board.mapper.FileMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FileDaoTests {
    @Autowired
    private FileDAO fileDAO;

//    @Test
//    public void insertTest(){
//        FileVO fileVO = new FileVO();
//        fileVO.setFileName("day04.txt");
//        fileVO.setUuid("4");
//        fileVO.setUploadPath("2022/06/10");
//        fileVO.setImage(false);
//        fileVO.setBoardBno(500L);
//
//        fileDAO.register(fileVO);
//    }

    @Test
    public void deleteTest(){
        fileDAO.remove(500L);
    }

//    @Test
//    public void findByBoardBnoTest(){
//        fileDAO.findByBoardBno(500L).stream().map(FileVO::toString).forEach(log::info);
//    }
}
