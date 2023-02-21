package kr.co.kmarket.controller;

import kr.co.kmarket.service.MyService;
import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_QnaVO;
import kr.co.kmarket.vo.product_reviewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("my/home")
    public String home() {
        return "my/home";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("my/ordered")
    public String ordered() {
        return "my/ordered";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("my/point")
    public String point() {
        return "my/point";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("my/coupon")
    public String coupon() {
        return "my/coupon";
    }

    /**
     * 2023/02/21 // 김재준 // review 기본 맵핑 생성
     * @return
     */
    @GetMapping("my/review")
    public String review(Model model, String pg, Integer revNo) {
        pg = (pg == null) ? "1" : pg;

        int count = 5;
        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage, count);
        long total = service.getCountTotalForReview(revNo);
        int lastPage = service.getLastPageNum(total, count);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        List<product_reviewVO> reviews = service.selectReviews(revNo, start);

        model.addAttribute("reviews", reviews);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("revNo", revNo);

        log.info("reviews : " + reviews);
        log.info("reviews.size : " + reviews.size());

        return "my/review";
    }

    /**
     * 2023/02/21 // 김재준 // qna 기본 맵핑 생성
     * @return
     */
    @GetMapping("my/qna")
    public String qna(Model model, String pg, Integer cate1) {
        pg = (pg == null) ? "1" : pg;

        int count = 10;
        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage, count);
        long total = service.getTotalCount(cate1);
        int lastPage = service.getLastPageNum(total, count);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        List<Cs_QnaVO> QnaArts = service.selectQnaArticles(start, cate1);

        model.addAttribute("QnaArts", QnaArts);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("cate1", cate1);

        return "my/qna";
    }

}
