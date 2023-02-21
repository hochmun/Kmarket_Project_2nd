package kr.co.kmarket.controller;

import kr.co.kmarket.entity.UserEntity;
import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.MyService;
import kr.co.kmarket.vo.Cs_QnaVO;
import kr.co.kmarket.vo.member_pointVO;
import kr.co.kmarket.vo.product_orderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 2023/02/20 // 심규영 // 기본 맵핑 생성
 */
@Controller
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
    public String home(Model model,
                       @AuthenticationPrincipal MyUserDetails myUserDetails) {
        // 유저 정보 불러오기
        UserEntity user = myUserDetails.getUser();
        
        // 최근 주문 내역
        List<product_orderVO> orderVOs = service.selectMyOrder(user.getUid());

        // 포인트 적립 내역
        List<member_pointVO> pointVOs = service.selectMyPointList5(user.getUid());

        // 상품평 내역

        // 문의 내역 내림 차순
        List<Cs_QnaVO> qnaVOs = service.selectMyQnaList5(user.getUid());

        // 전송 모델
        model.addAttribute("orderVOs", orderVOs);
        model.addAttribute("pointVOs", pointVOs);
        model.addAttribute("qnaVOs", qnaVOs);

        // 리턴
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

}
