package kr.co.kmarket.controller.cs.faq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqListController {

    @GetMapping(value = {"cs/faq/list"})
    public String list(){
        return "cs/faq/list";
    }
}
