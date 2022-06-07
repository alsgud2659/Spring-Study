package com.example.board.controller;

import com.example.board.domain.vo.BoardVO;
import com.example.board.domain.vo.Criteria;
import com.example.board.domain.vo.PageDTO;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

/*
 *   Task        URL                 Method      Parameter       Form        URL 이동
 *
 *   전체목록     /board/list          GET
 *   등록처리     /board/register      POST        모든 항목        필요         /board/list
 *   조회        /board/read          GET         bno
 *   삭제처리     /board/remove        GET         bno                         /board/list
 *   수정처리     /board/modify        POST        모든 항목        필요         /board/list
 *
 * */

@Controller // 컨트롤러임을 등록
@Slf4j  // log를 사용할 수 있게 해줌
@RequestMapping("/board/*") // /board/ 로 주소가 시작하는 페이지를 인식
@RequiredArgsConstructor    // final 혹은 @NONNULL이 붙은 필드에 생성자를 추가
public class BoardController {
    private final BoardService boardService; // 보드 서비스 필드 생성

    @GetMapping("/list") // /list로 끝나는 get 요청 인식
//    리턴 타입을 void로 작성해도 되지만,
//    다른 컨트롤러에서 getList()를 호출하기 때문에
//    html 경로를 직접 문자열로 작성해야 한다.
    public String getList(Criteria criteria, Model model){ // 화면에 뿌려줘야 하기 때문에 model객체를 인자로 받는다
        // 한페이지에 10개씩 페이징을 처리하기 위해 criteria 객체도 인자로 받는다
        log.info("*************");
        log.info("/list");
        log.info("*************");
        model.addAttribute("boardList", boardService.getList(criteria)); // 보드서비스의 getList로 리스트를 받아서
        // 모델객체에 담는다
        model.addAttribute("pageDTO", new PageDTO(criteria, boardService.getTotal()));
        // 총개시글 수를 알기 위해 getTotal메소드의 값을 모델객체에 담는다
        return "/board/list"; // 해당 URL을 리턴
    }

    @GetMapping("/register") // /register로 끝나는 get요청을 인식
    public void register(){} // 게시글 등록 페이지로 이동시킴

    @PostMapping("/register") // /register로 끝나는 post 요청을 인식
    public RedirectView register(BoardVO boardVO, RedirectAttributes rttr){ // 게시글 상세내용을 담은 boardVO객체를
        // 리다이렉트 방식으로 보내기위한 RedirectAttribute객체를 인자로 받는다
        log.info("*************");
        log.info("/register");
        log.info("*************");
        boardService.register(boardVO); // boardService의 register메소드로 작성된 내용을 쿼리문으로 db에 저장

//        redirect 방식으로 전송할 때에는 request scope를 사용할 수 없다.
//        RedirectAttributes 객체는 두 가지 방법을 제공해준다.
//        1. 쿼리 스트링
//          URL 뒤에 전달한 KEY와 VALUE를 쿼리스트링으로 연결해준다.
//        rttr.addAttribute("boardBno", boardVO.getBoardBno());

//        2. Flash 사용
//          세션에 파라미터를 저장하고, request객체가 초기화 된 후 다시 저장해준다.
        rttr.addFlashAttribute("boardBno", boardVO.getBoardBno());

        return new RedirectView("/board/list"); // 해당 URL로 보낸다
    }

    @GetMapping({"/read", "/modify"}) // /read 나 / modify로 끝나는 get요청 인식
    public void read(Long boardBno, Criteria criteria, HttpServletRequest request, Model model){
        // db에서 번호로 select를 하기 때문에 boardBno, 페이지를 기억하기 위해 criteria객체, 해당값을 화면에 출력하기 위해
        // model객체를 인자로 받는다
        log.info("*************");
        String requestURL = request.getRequestURI();
        log.info(requestURL.substring(requestURL.lastIndexOf("/")));
        log.info("*************");
        log.info("================================");
        log.info(criteria.toString());
        log.info("================================");
        model.addAttribute("board", boardService.read(boardBno)); // 모델객체에 db에서 가져온 값을 넣는다
    }

    //    수정
//    Redirect 방식으로 전송
//    Flash로 데이터 전달 - 수정 성공 시에만 "success" 전달
    @PostMapping("/modify") // 수정 페이지로 이동했을 때
    public RedirectView modify(BoardVO boardVO, Criteria criteria, RedirectAttributes rttr){
        // 수정된 내용을 담을 boardVO와 수정완료후 리스트로 갔을 때 페이지를 기억하기 위해 criteria객체, 리다이렉트 방식으로
        // 보내기위해 RedirectAttribute를 인자로 받는다
        log.info("*************");
        log.info("/modify");
        log.info("*************");
        log.info("================================");
        log.info(criteria.toString());
        log.info("================================");
        if(boardService.modify(boardVO)){
//            컨트롤러에서 다른 컨트롤러의 매개변수로 파라미터를 전달할 때에는
//            addAttribute(), 쿼리스트링 방식으로 전달해야 받을 수 있다.
//            Flash방식은 최종 응답 화면에서 사용될 파라미터를 전달할 때에만 사용하도록 한다.
            rttr.addAttribute("boardBno", boardVO.getBoardBno());
            rttr.addAttribute("pageNum", criteria.getPageNum());
            rttr.addAttribute("amount", criteria.getAmount());
        }
        return new RedirectView("/board/read"); // 수정 완료후 해당 게시글의 상세보기로 이동
    }

    @PostMapping("/remove")
    public String remove(Long boardBno, Criteria criteria, Model model){
        // 게시글 번호로 해당 게시글을 삭제하기 때문에 boardBno, 삭제후 리스트로 돌아가면 페이징 처리가 될 수 있게 Criteria객체
        // getList가 model객체를 인자로 받기로 했기때문에 model객체도 함께 인자로 받는다.
        log.info("*************");
        log.info("/remove");
        log.info("*************");
        boardService.remove(boardBno);
        //다른 컨트롤러로 이동하고자 할 때 해당 메소드를 직접 실행한다.
        //만약 필요한 파라미터가 있다면 최초 요청 처리 메소드를 통해 파라미터를 전달해준다.
        return getList(criteria, model);
    }
}

