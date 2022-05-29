package com.example.ex02.controller;

import com.example.ex02.domain.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/ex/*")
@Slf4j
public class ExampleController {

    // value에 경로를 작성하고, method에 호출할 서블릿 메소드를 설정한다.
    @RequestMapping(value = "/example", method = {RequestMethod.GET, RequestMethod.POST})
    public void ex01(){
        log.info("ex01.........");
    }

    @GetMapping("")
    public void ex02() {
        log.info("ex02.........");
    }

    @GetMapping("/ex03")
    public String ex03(ExampleVO exampleVo) {
        log.info("------------------");
        log.info(exampleVo.toString());
        log.info("------------------");
        return "ex03";
    }

    @GetMapping("/ex04")
    public String ex04(TaskVO taskVO) {
        log.info("------------------");
        log.info(taskVO.toString());
        log.info("------------------");
        return "ex04";
    }

    //실습

//    @GetMapping("/login")
//    public void login() {}
//
//    @PostMapping("/login")
//    public String login(UserVO userVO){
//        if (userVO.getId().equals("admin")){
//            return "admin";
//        }else {
//            return "user";
//        }
//    }

    //실습
    @GetMapping("/work")
    public String work() {return "checkWork";}

    @PostMapping("/work")
    public String work(WorkVO workVO){
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
        int nowTime = Integer.parseInt(simpleDateFormat.format(nowDate));
        // int nowTime = 1;

        if (nowTime >= 9 && nowTime < 18 && workVO.getWorkStatus().equals("출근")){
            return "work/late";
        }else if(nowTime >= 9 && nowTime <= 18 && workVO.getWorkStatus().equals("퇴근")) {
            return "work/work";
        }else if((nowTime >= 18 || nowTime < 9) && workVO.getWorkStatus().equals("퇴근")) {
            return "work/leaveWork";
        }else if((nowTime < 9 || nowTime >= 18) && workVO.getWorkStatus().equals("출근")) {
            return "work/getToWork";
        }

        return "";
    }

    //실습

    // ScrollVO 클래스 선언
    // 10% 60% 100% 주문서의 공격력 수치를 저장한다.
    // 기본 생성자를 호출 했을 떄에는 위에 작성된 공격력 수치를 기본 값으로 설정하고,
    // 만약 새로운 값을 받게 되면 해당 공격력 수치로 변경되도록 생성자를 오버로딩한다.

    @GetMapping("/upgrade")
    public String upgrade() {return "upgrade/form";}

    @PostMapping("/upgrade")
    public String upgrade(String choice, Model model) {
        log.info("*************** choice :::" + choice);
        ScrollVO scrollVO = new ScrollVO();

        int strength = 0;
        boolean check = false;
        switch(Integer.parseInt(choice)){
            case 0: //10%
                check = getChance(10);
                strength = scrollVO.getScroll10();
                break;
            case 1: //60%
                check = getChance(60);
                strength = scrollVO.getScroll60();
                break;
            case 2: //100%
                check = getChance(100);
                strength = scrollVO.getScroll100();
                break;
        }
        if(!check){ return "upgrade/fail";}
        if(getChance(100)){
            strength *= 5;
            model.addAttribute("strength", strength);
            return "upgrade/superSuccess";
        }
        model.addAttribute("strength", strength);
        return "upgrade/success";
    }

    public boolean getChance(int rating){
        Random r = new Random();
        int[] numbers = new int[10];
        int index = r.nextInt(numbers.length);
        for (int i=0; i<rating/10; i++){
            numbers[i] = 1;
        }

        return numbers[index] == 1;
    }

    // 실습
    // 사용자가 입력한 바코드 번호에 알맞는 상품명을 전달한다.
    @GetMapping("/market")
    public String gomarket() {
        return "product/market";
    }

    @GetMapping("/check")
    public String check(String barcode, Model model) {
        String productName = null;
        if (barcode.equals("4383927")) {
            productName = "오징어땅콩";
        } else if (barcode.equals("0832147")) {
            productName = "초코우유";
;        } else if (barcode.equals("9841631")) {
            productName = "벌꿀피자";
        } else if (barcode.equals("5587578")) {
            productName = "샌드위치";
        }
        model.addAttribute("productName", productName);
        return "product/casher";
    }

    // 실습
    @GetMapping("/login")
    public void login() {}

    @PostMapping("/login")
    public String login(UserVO userVO) {
        if (userVO.getId().equals("apple") && userVO.getPw().equals("banana")){
            return "loginSuccess";
        }else {
            return "loginFail";
        }
    }

    // 실습 (노래방 기계 제작)
    // 사용자의 점수에 따른 알맞는 메세지 출력
    @GetMapping("/singing")
    public String sing() {
        return "/singingroom/singingForm";
    }
    @PostMapping("/singing")
    public String sing(String score, Model model){
        String mention = null;
        if(Integer.parseInt(score) >= 90){
            mention = "노래를 굉장히 잘 하시네요~";
        } else if(Integer.parseInt(score) >= 70) {
            mention = "평범하네요~";
        } else if(Integer.parseInt(score) <= 50) {
            mention = "노래를 너무 못해요~";
        }

        model.addAttribute("mention", mention);
        return "/singingroom/singingResult";
    }

    // 숙제
    // 식당예약
    @GetMapping("/reservation")
    public String reservation() {return "/reservation/reservationForm";}

    @PostMapping("/reservation")
    public String reservation(ReservationVO reservationVO, Model model) {
        String name = reservationVO.getName();
        String date = reservationVO.getDate();
        String time = reservationVO.getTime();
        String people = reservationVO.getPeople();

        model.addAttribute("name", name);
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("people", people);

        return "/reservation/checkReservation";
    }

    // 도서조회
    @GetMapping("/searchBook")
    public String searchBook() { return "/searchBook/searchBook"; }

    @PostMapping("/searchBook")
    public String searchBook(String bookTitle, Model model) {
        String bookStatus = null;
        if (bookTitle.equals("12가지인생의법칙")) {
            bookStatus = "가능";
        } else if(bookTitle.equals("컴퓨터아키텍쳐")) {
            bookStatus = "가능";
        } else if(bookTitle.equals("쉽게배우는알고리즘")) {
            bookStatus = "불가능";
        } else if(bookTitle.equals("JAVA의정석")) {
            bookStatus = "불가능";
        } else if(bookTitle.equals("VOCABible")) {
            bookStatus = "가능";
        }
        log.info("****************");
        log.info(bookTitle);
        log.info(bookStatus);
        log.info("****************");
        model.addAttribute("bookStatus", bookStatus);
        model.addAttribute("bookTitle", bookTitle);
        return "/searchBook/searchResult";
    }

    // 랜덤박스 개봉
    @GetMapping("openBox")
    public String openBox() { return "/openBox/openIt"; }

    @PostMapping("openBox")
    public String openBoxResult() {
        boolean result = true;
        result = getChance(30);

        if(!result) {return "/openBox/openingFail";}
        else {return "/openBox/openingWinning";}

    }


    @GetMapping("/info")
//    @ModelAttribute("KEY") Object obj
//    전달받은 파라미터를 화면쪽으로 보낼 때 쉽고 간편하게 사용할 수 있다.
//    여러 개의 데이터를 보낼 때에는 Model 데이터 전달자를 사용하고,
//    2개 이하의 데이터를 보낼 때에는 @ModelAttribute()를 사용하는 것이 좋다.
    public void getInfo(@ModelAttribute("name") String name, @ModelAttribute("age") Integer age){
    }

    @GetMapping("/datas")
//    동일한 이름의 파라미터가 여러 개 들어올 때에는 배열 또는 List로 매개변수를 설정한다.
//    이 때 동일한 이름으로 받아야 하기 때문에 @RequestParam("KEY")을 사용해서 전달받을 데이터의 KEY값을 지정해준다.
//    KEY 파라미터명이 전달되면 뒤에 있는 매개변수로 들어간다.
    public void getDatas(@RequestParam("data") ArrayList<Integer> datas){
        log.info(String.valueOf(datas.size()));
        datas.stream().map(String::valueOf).forEach(log::info);
    }

    @GetMapping("/different")
//    파라미터 명과 매개변수 명이 다르면 직접 지정해준다.
    public void getData(@RequestParam("data") String name){

    }

}
