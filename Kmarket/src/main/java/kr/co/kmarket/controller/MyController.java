package kr.co.kmarket.controller;

import kr.co.kmarket.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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

}
