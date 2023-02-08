package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MemberController {

    @GetMapping("member/join")
    public String join(){
        return "member/join";
    }
    @GetMapping("member/login")
    public String login(){
        return "member/login";
    }
    @GetMapping("member/register")
    public String register(){
        return "member/register";
    }
    @GetMapping("member/registerSeller")
    public String registerSeller(){
        return "member/registerSeller";
    }
    @GetMapping("member/signup")
    public String signup(){
        return "member/signup";
    }
 @GetMapping("member/signupSeller")
    public String signupSeller(){
        return "member/signupSeller";
    }

}
