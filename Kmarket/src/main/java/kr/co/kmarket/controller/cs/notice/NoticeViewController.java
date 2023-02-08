package kr.co.kmarket.controller.cs.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeViewController {

    @GetMapping(value = {"cs/notice/view"})
    public String view(){
        return "cs/notice/view";
    }
}
