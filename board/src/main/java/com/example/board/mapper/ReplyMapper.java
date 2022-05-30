package com.example.board.mapper;

import com.example.board.domain.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyMapper {
    // 댓글 추가
    public void insertReply(ReplyVO replyVO);
    // 댓글 1개 조회
    public ReplyVO getReply(Long replyNumber);
}
