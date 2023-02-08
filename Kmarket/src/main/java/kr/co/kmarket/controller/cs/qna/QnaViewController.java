package kr.co.kmarket.controller.cs.qna;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QnaViewController {

    @GetMapping(value = {"cs/qna/view"})
    public String view(){
        return "cs/qna/view";
    }
}
