package kr.co.kmarket.controller.cs.faq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FaqViewController {

    @GetMapping(value = {"cs/faq/view"})
    public String view(){
        return "cs/faq/view";
    }
}
