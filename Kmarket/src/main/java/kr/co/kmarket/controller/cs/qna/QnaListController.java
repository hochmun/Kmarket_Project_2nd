package kr.co.kmarket.controller.cs.qna;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QnaListController {

    @GetMapping(value = {"cs/qna/list"})
    public String list(){
        return "cs/qna/list";
    }
}
