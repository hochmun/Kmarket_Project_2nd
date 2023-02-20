package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TermsPolicyController {

    @GetMapping("/termsPolicy/policy")
    public String termsPolicy(){
        return "/termsPolicy/policy";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("/termsPolicy/buyer")
    public String buyer(){
        return "/termsPolicy/buyer";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("/termsPolicy/seller")
    public String seller(){
        return "/termsPolicy/seller";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("/termsPolicy/finance")
    public String finance(){
        return "/termsPolicy/finance";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("/termsPolicy/location")
    public String location(){
        return "/termsPolicy/location";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("/termsPolicy/privacy")
    public String privacy(){
        return "/termsPolicy/privacy";
    }
}
