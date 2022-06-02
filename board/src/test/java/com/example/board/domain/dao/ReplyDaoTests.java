package com.example.board.domain.dao;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import com.example.board.domain.dao.ReplyDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyDaoTests {
    @Autowired
    private ReplyDAO replyDAO;

    private Long[] arBno = {14L, 15L, 16L, 17L, 18L};

    @Test
    public void Test() {
        log.info(replyDAO+"");
    }

    @Test
    public void replyInsertTest() {
        IntStream.rangeClosed(1,10).forEach(i -> {
            ReplyVO replyVO = new ReplyVO();
            replyVO.setBoardBno(arBno[i % 5]);
            replyVO.setReplyContent("새로운 댓글" + i);
            replyVO.setReplyWriter("댓글 작성자" + i);
            // replyDAO의 insertReply 실행
            replyDAO.register(replyVO);
        });
    }

//    @Test
//    public void modifyTest() {
//        ReplyVO replyVO = new ReplyVO();
//        replyVO.setBoardBno(24L);
//        replyVO.setReplyContent("수정된 댓글");
//        replyVO.setReplyWriter("수정된 댓글 작성자");
//        // replyDAO의 insertReply 실행
//        replyDAO.register(replyVO);
//    }

//    @Test
//    public void getReply() {
//        // 가져올 댓글 번호
//        Long replyNumber = 1L;
//        // 결과 출력
//        log.info("가져온 댓글 : " + replyDAO.getReply(replyNumber).toString());
//    }

//    @Test
//    public void getListTest(){
//        replyDAO.getList(new Criteria(2, 10), 14L)
//                .stream().map(ReplyVO::toString).forEach(log::info);
//    }
}
