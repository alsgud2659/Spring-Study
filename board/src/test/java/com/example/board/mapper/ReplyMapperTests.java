package com.example.board.mapper;

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Slf4j
public class ReplyMapperTests {
    @Autowired
    private ReplyMapper replyMapper;

    private Long[] arBno = {14L, 15L, 16L, 17L, 18L};

//    @Test
//    public void replyMapperTest() {
//        log.info(replyMapper+"");
//    }

//    @Test
//    public void replyInsertTest() {
//        IntStream.rangeClosed(1,10).forEach(i -> {
//            ReplyVO replyVO = new ReplyVO();
//            replyVO.setBoardBno(arBno[i % 5]);
//            replyVO.setReplyContent("새로운 댓글" + i);
//            replyVO.setReplyWriter("댓글 작성자" + i);
//            // ReplyMapper의 insertReply 실행
//            replyMapper.insert(replyVO);
//        });
//    }

//    @Test
//    public void getReply() {
//        // 가져올 댓글 번호
//        Long replyNumber = 1L;
//        // 결과 출력
//        log.info("가져온 댓글 : " + replyMapper.getReply(replyNumber).toString());
//    }

    @Test
    public void getListTest(){
        replyMapper.getList(new Criteria(2, 10), 14L)
                .stream().map(ReplyVO::toString).forEach(log::info);
    }

}
