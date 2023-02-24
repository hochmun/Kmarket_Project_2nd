package kr.co.kmarket.controller;

import kr.co.kmarket.entity.UserEntity;
import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.service.MyService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 2023/02/20 // 심규영 // 기본 맵핑 생성
 */
@Controller
@Slf4j
public class MyController {

    /**
     * 2023/02/21 // 심규영 // 서비스 연결
     */
    @Autowired
    private MyService service;

    @Autowired
    private MemberService memberservice;

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("my/home")
    public String home(Model model,
                       @AuthenticationPrincipal MyUserDetails myUserDetails, String cate) {
        // 유저 정보 불러오기
        UserEntity user = myUserDetails.getUser();
        
        // 최근 주문 내역
        List<product_orderVO> orderVOs = service.selectMyOrder(user.getUid());

        // 포인트 적립 내역
        List<member_pointVO> pointVOs = service.selectMyPointList5(user.getUid());

        // 상품평 내역
        List<product_reviewVO> reviewVOs = service.selectMyReviewList5(user.getUid());

        // 문의 내역 내림 차순
        List<Cs_QnaVO> qnaVOs = service.selectMyQnaList5(user.getUid());

        // 전송 모델
        model.addAttribute("orderVOs", orderVOs);
        model.addAttribute("pointVOs", pointVOs);
        model.addAttribute("reviewVOs", reviewVOs);
        model.addAttribute("qnaVOs", qnaVOs);
        model.addAttribute("user", user);
        
        // aside 용
        model.addAttribute("cate", cate);



        // 리턴
        return "my/home";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * 2023/02/22 // 김재준 // 주문 내역 출력
     * @return
     */
    @GetMapping("my/ordered")
    public String ordered(Model model, String pg, String date,
                          @AuthenticationPrincipal MyUserDetails myUser, String cate) {
        // 유저 정보 불러오기
        UserEntity user = myUser.getUser();

        // 현재 날짜
        LocalDate now = LocalDate.now();
        // 현재 월
        int month = now.getMonthValue();

        String[] monthNames = {"fourMonth", "threeMonth", "twoMonth", "aMonth", "thisMonth"};

        // 날짜가 없을 경우
        if(date == null) {
            date = "none";
        }else if(date.equals("week")) {
            // 1주일
            now.minusWeeks(1);
        }else if(date.equals("halfMonth")) {
            // 보름
            now.minusDays(15);
        }else if(date.equals("month")) {
            // 1개월
            now.minusMonths(1);
        }else if(date.equals("fourMonth")) {
            // 4달전
            now.minusMonths(4);
        }else if(date.equals("threeMonth")) {
            // 3달전
            now.minusMonths(3);
        }else if(date.equals("twoMonth")) {
            // 2달전
            now.minusMonths(2);
        }else if(date.equals("aMonth")) {
            // 1달전
            now.minusMonths(1);
        }else if(date.equals("thisMonth")) {
            // 이번달
            now.minusMonths(0);
        }

        // 페이지 번호가 없을 경우 1로 초기화
        pg = (pg == null) ? "1" : pg;

        int count = 10;
        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage, count);
        long total = service.getCountTotalForOrder(user.getUid());
        int lastPage = service.getLastPageNum(total, count);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        // 최근 주문 내역
        List<product_orderVO> orderVOs = service.selectMyOrdered(user.getUid(), start, date);

        model.addAttribute("orderVOs", orderVOs);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("month", month);
        model.addAttribute("monthNames", monthNames);
        model.addAttribute("date", date);

        // aside 용
        model.addAttribute("cate", cate);

        return "my/ordered";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * 23/02/22 // 김재준 // 포인트 내역 출력
     * @return
     */
    @GetMapping("my/point")
    public String point(Model model, String pg, String date,
                        @AuthenticationPrincipal MyUserDetails myUser, String cate) {
        // 유저 정보 불러오기
        UserEntity user = myUser.getUser();

        // 현재 날짜
        LocalDate now = LocalDate.now();
        // 현재 월
        int month = now.getMonthValue();

        String[] monthNames = {"fourMonth", "threeMonth", "twoMonth", "aMonth", "thisMonth"};

        // 날짜가 없을 경우
        if(date == null) {
            date = "none";
        }else if(date.equals("week")) {
            // 1주일
            now.minusWeeks(1);
        }else if(date.equals("halfMonth")) {
            // 보름
            now.minusDays(15);
        }else if(date.equals("month")) {
            // 1개월
            now.minusMonths(1);
        }else if(date.equals("fourMonth")) {
            // 4달전
            now.minusMonths(4);
        }else if(date.equals("threeMonth")) {
            // 3달전
            now.minusMonths(3);
        }else if(date.equals("twoMonth")) {
            // 2달전
            now.minusMonths(2);
        }else if(date.equals("aMonth")) {
            // 1달전
            now.minusMonths(1);
        }else if(date.equals("thisMonth")) {
            // 이번달
            now.minusMonths(0);
        }

        // 페이지 번호가 없을 경우 1로 초기화
        pg = (pg == null) ? "1" : pg;

        int count = 10;
        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage, count);
        long total = service.getCountTotalForPoint(user.getUid());
        int lastPage = service.getLastPageNum(total, count);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        // 포인트 적립 내역
        List<member_pointVO> pointVOs = service.selectMyPoint(user.getUid(), start, date);

        model.addAttribute("pointVOs", pointVOs);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("month", month);
        model.addAttribute("monthNames", monthNames);
        model.addAttribute("date", date);

        // aside 용
        model.addAttribute("cate", cate);

        return "my/point";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * 2023/02/33 // 이해빈 // 쿠폰 목록 가져오기
     * @return
     */
    @GetMapping("my/coupon")
    public String coupon(Model model, @AuthenticationPrincipal MyUserDetails myUser, String cate) {

        String uid = myUser.getUser().getUid();

        // 전체 쿠폰 가져오기
        List<CouponVO> coupons = service.selectCoupons(uid);

        // 현재 사용가능한 쿠폰 갯수
        int count = service.getCouponCount(uid);

        model.addAttribute("coupons", coupons);
        model.addAttribute("count", count);

        // aside 용
        model.addAttribute("cate", cate);

        return "my/coupon";
    }

    /**
     * 2023/02/21 // 김재준 // review 기본 맵핑 생성
     * // 리뷰 출력
     * @return
     */
    @GetMapping("my/review")
    public String review(Model model, String pg, Integer revNo, Integer cate1, Integer cate2, @AuthenticationPrincipal MyUserDetails myUser , String cate) {
        // 유저 정보 불러오기
        UserEntity user = myUser.getUser();

        // 페이지 번호가 없을 경우 1로 초기화
        pg = (pg == null) ? "1" : pg;

        // 페이지당 출력할 게시물 수
        int count = 5;
        // 현재 페이지
        int currentPage = service.getCurrentPage(pg);
        // 시작 게시물 번호
        int start = service.getLimitStart(currentPage, count);
        // 전체 게시물 수
        long total = service.getCountTotalForReview(user.getUid());
        // 마지막 페이지
        int lastPage = service.getLastPageNum(total, count);
        // 페이지 시작 번호
        int pageStartNum = service.getPageStartNum(total, start);
        // 페이지 그룹
        int groups[] = service.getPageGroup(currentPage, lastPage);

        // 리뷰 list 출력
        List<product_reviewVO> reviews = service.selectReviews(revNo, start, cate1, cate2, user.getUid());

        // 리뷰란 상품명 클릭 시 product/view 하이퍼링크를 위한 상품 list
        List<productVO> products = service.selectProducts(cate1, cate2, start);

        model.addAttribute("reviews", reviews);
        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("revNo", revNo);
        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);

        // aside 용
        model.addAttribute("cate", cate);

        return "my/review";
    }

    /**
     * 2023/02/21 // 김재준 // qna 기본 맵핑 생성
     * // 문의 내역 출력
     * @return
     */
    @GetMapping("my/qna")
    public String qna(Model model, String pg, Integer cate1, @AuthenticationPrincipal MyUserDetails myUser, String cate){
        // 유저 정보 불러오기
        UserEntity user = myUser.getUser();

        // 페이지 번호가 없을 경우 1로 초기화
        pg = (pg == null) ? "1" : pg;

        int count = 10;
        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage, count);
        long total = service.getTotalCount(cate1, user.getUid());
        int lastPage = service.getLastPageNum(total, count);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        // 문의 list 출력
        List<Cs_QnaVO> QnaArts = service.selectQnaArticles(start, cate1, user.getUid());

        model.addAttribute("QnaArts", QnaArts);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("cate1", cate1);

        // aside 용
        model.addAttribute("cate", cate);

        return "my/qna";
    }


    /**
     * 2023/02/22 // 이해빈 // info 기본 맵핑 생성
     */
    @GetMapping("my/info")
    public String info(Model model, @AuthenticationPrincipal MyUserDetails myUser, String cate){

        String uid = myUser.getUser().getUid();
        
        // 유저 정보 가져오기
        memberVO member = memberservice.selectMember(uid);

        model.addAttribute("member", member);
        model.addAttribute("cate", cate);

        return "my/info";
    }

    @ResponseBody
    @PostMapping("my/info")
    public Map<String, Integer> info(memberVO vo){
        int result = 0;
        
        // 회원정보 수정
        result = service.updateMember(vo);


        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }
}
