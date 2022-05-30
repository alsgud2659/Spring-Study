package com.example.board.mapper;

import com.example.board.domain.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ReplyMapperTests {
    @Autowired
    private ReplyMapper replyMapper;

    @Test
    public void replyMapperTest() {
        log.info(replyMapper+"");
    }

    @Test
    public void replyInsertTest() {
        // ReplyVO객체 생성
        ReplyVO replyVO = new ReplyVO();
        // replyVO에 setter메소드로 값을 채워줌
        replyVO.setReplyNumber(1L);
        replyVO.setBoardBno(1L);
        replyVO.setReplyContent("새로운 댓글1");
        replyVO.setReplyWriter("댓글 작성자1");

        // ReplyMapper의 insertReply 실행
        replyMapper.insertReply(replyVO);

        //결과 출력
        log.info("댓글 번호 : " + replyVO.getReplyNumber());
    }

    @Test
    public void getReply() {
        // 가져올 댓글 번호
        Long replyNumber = 1L;
        // 결과 출력
        log.info("가져온 댓글 : " + replyMapper.getReply(replyNumber));
    }

}
