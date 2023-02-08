package kr.co.kmarket.controller.cs.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeListController {

    @GetMapping(value = {"cs/notice/list"})
    public String list(){
        return "cs/notice/list";
    }
}
