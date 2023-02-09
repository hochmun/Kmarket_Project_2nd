package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 23/02/09 김재준 회사소개(company) Controller
 */
@Controller
public class CompanyController {
    @GetMapping(value = {"company/index"})
    public String index(){
        return "company/index";
    }

    @GetMapping(value = {"company/introduce"})
    public String introduce(){
        return "company/introduce";
    }

    @GetMapping(value = {"company/manage"})
    public String manage(){
        return "company/manage";
    }

    @GetMapping(value = {"company/notice"})
    public String notice(){
        return "company/notice";
    }

    @GetMapping(value = {"company/promote"})
    public String promote(){
        return "company/promote";
    }
}
