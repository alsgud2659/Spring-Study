package com.example.board.controller;
/*
* REST (Representational State Transfer)
*   하나의 URI는 하나의 고유한 리소르(데이터)를 대표하도록 설계된다.
*   예) /board/123 : 게시글 중 123번 게시글의 전체 정보
*
*/

import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.ReplyVO;
import com.example.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/reply/*")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    // 댓글 등록
    // 브라우저에서 json 타입으로 데이터를 전송하고 서버에서는 댓글의 처리결과에 따라 문자열로 결과를 리턴한다.
    // consumes : 전달받은 데이터의 타입
    // produces : 콜백함수로 결과를 전달할 때의 타입
    // @RequestBody : 전달받은 데이터를 알맞는 매개변수로 주입할 때 사용한다.
    // ResponseEntity : 서버의 상태코드, 응답메세지 등을 담을 수 있는 타입
    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> create(@RequestBody ReplyVO replyVO) throws UnsupportedEncodingException {
        log.info("replyVO : " + replyVO);
        replyService.register(replyVO);
        return new ResponseEntity<>(new String("댓글 등록 성공".getBytes(), "UTF-8"), HttpStatus.OK);
    }

    // 댓글 1개 조회
    @GetMapping("/{rno}")
    public ReplyVO read(@PathVariable("rno") Long replyNumber) {
        log.info("read....... : " + replyNumber);
        return replyService.read(replyNumber);
    }

    // 댓글 전체 목록 조회
    @GetMapping("/list/{bno}/{page}")
    public List<ReplyVO> getList(@PathVariable("page") int pageNum, @PathVariable("bno") Long boardBno){
        log.info("list....... : " + boardBno);
        return replyService.getList(new Criteria(pageNum,10), boardBno);
    }

    // 댓글 삭제
    @DeleteMapping("/remove/{rno}")
    public String remove(@PathVariable("rno") Long replyNumber){
        replyService.remove(replyNumber);
        return "댓글 삭제 성공";
    }

    // 댓글 수정
    // PUT : 자원의 전체 수정, 자원 내 모든 필드를 전달해야 함, 일부만 전달할 경우 오류
    // PATCH : 자원의 일부 수정, 수정할 필드만 전송 (자동주입이 아닌 부분만 수정하는 쿼리문에서 사용)
    //
    @PatchMapping(value = {"/{rno}/{writer}", "/{rno}"}, consumes = "application/json")
    public String modify(@PathVariable("rno") Long replyNumber, @PathVariable(value = "writer", required = false) String replyWriter, @RequestBody ReplyVO replyVO){
        log.info("modify......... : " + replyVO);
        log.info("modify......... : " + replyNumber);

        if(replyVO.getReplyWriter() == null){ // JSON 검증
            replyVO.setReplyWriter(Optional.ofNullable(replyWriter).orElse("anonymous")); // URI 검증
        }
        replyVO.setReplyNumber(replyNumber); // null값을 PATCH에서 직접 채워주는게 아니기 때문에 replyNumber를 반드시 채워줘야 함
        replyService.modify(replyVO);
        return "댓글 수정 성공";
    }

    // POST방식으로 댓글 수정
    @PostMapping("/modify/{rno}/{replyWriter}")
    public boolean modify2(@PathVariable("rno") Long replyNumber, @PathVariable("replyWriter") String replyWriter, @RequestBody ReplyVO replyVO){
        log.info("modify ...... : " + replyVO);
        replyVO.setReplyNumber(replyNumber);
        replyVO.setReplyWriter(replyWriter);
        return replyService.modify(replyVO);
    }
}
