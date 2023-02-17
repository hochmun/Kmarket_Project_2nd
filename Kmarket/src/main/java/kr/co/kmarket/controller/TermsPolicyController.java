package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TermsPolicyController {

    @GetMapping("/termsPolicy/policy")
    public String termsPolicy(){
        return "/termsPolicy/policy";
    }
}
