package kr.co.kmarket.controller.cs.qna;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QnaWriteController {

    @GetMapping(value = {"cs/qna/write"})
    public String write(){
        return "cs/qna/write";
    }
}
