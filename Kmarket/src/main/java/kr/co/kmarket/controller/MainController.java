package kr.co.kmarket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 메인 index 컨트롤러
 * @since 2023/02/08
 * @author 심규영
 */
@Controller
public class MainController {

    /**
     * 메인 인덱스 Get맵핑
     * @since 2023/02/08 심규영
     * @return String
     */
    @GetMapping(value = {"/", "/index"})
    public String index() {
        return "index";
    }

}
