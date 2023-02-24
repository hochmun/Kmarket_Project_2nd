package kr.co.kmarket.controller;

import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.vo.member_termsVO;
import kr.co.kmarket.vo.memberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    private MemberService service;

    @GetMapping("member/join")
    public String join(){
        return "member/join";
    }
    @GetMapping("member/login")
    public String login(){
        return "member/login";
    }

    @GetMapping("member/search_id")
    public String search_id(){
        return "member/search_id";
    }

    @PostMapping("member/search_id_Result")
    public String search_id_Result(HttpServletResponse response, @RequestParam("name") String name,Model md) throws IOException {
        md.addAttribute("id", service.search_id(response, name));
        return "member/search_id_Result";
    }

    @GetMapping("member/search_pw")
    public String search_pwd(){
        return "member/search_pw";
    }

    /*
    @PostMapping("member/search_pw")
    public String search_pwd(){
        return "member/search_pw";
    }*/
    @GetMapping("member/register")
    public String register(){
        return "member/register";
    }

    @PostMapping("member/register")
    public String register(memberVO vo, HttpServletRequest req){
        vo.setRegip(req.getRemoteAddr());
        int result = service.insertMember(vo);
        return "redirect:/member/login?success"+result;
    }
    @PostMapping("member/registerSeller")
    public String registerSeller(memberVO vo, HttpServletRequest req){
        vo.setRegip(req.getRemoteAddr());
        int result = service.insertSellerMember(vo);
        return "redirect:/member/login?success"+result;
    }
    @GetMapping("member/registerSeller")
    public String registerSeller(){
        return "member/registerSeller";
    }
    @GetMapping("member/signup")
    public String signup(Model model){
        member_termsVO vo = service.selectTerms();
        model.addAttribute(vo);
        return "member/signup";
    }

    @GetMapping("member/signupSeller")
    public String signupSeller(Model model){
        member_termsVO vo = service.selectTerms();
        model.addAttribute(vo);
        return "member/signupSeller";
    }

    @ResponseBody
    @GetMapping("member/checkUid")
    public Map<String, Integer> checkUid(@RequestParam("uid") String uid){
        int result = service.countByUid(uid);
        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);
        return resultMap;
    }


}
