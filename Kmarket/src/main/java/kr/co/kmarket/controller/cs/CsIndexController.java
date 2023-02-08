package kr.co.kmarket.controller.cs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CsIndexController {
    @GetMapping(value = {"cs/index"})
    public String index(){
        return "cs/index";
    }
}