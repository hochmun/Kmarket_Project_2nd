package kr.co.kmarket.controller;

import kr.co.kmarket.service.MainService;
import kr.co.kmarket.vo.productVO;
import kr.co.kmarket.vo.product_cate1VO;
import kr.co.kmarket.vo.product_cate2VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 메인 index 컨트롤러
 * @since 2023/02/08
 * @author 심규영
 */
@Controller
public class MainController {

    /**
     * 2023/02/10 // 심규영 // 서비스 연결
     */
    @Autowired
    private MainService service;

    /**
     * 메인 인덱스 Get맵핑
     * @since 2023/02/08 심규영 <br>
     * @return String
     */
    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        List<product_cate1VO> cate1s = service.selectCate1s();
        List<product_cate2VO> cate2s = service.selectCate2s();

        List<productVO> bests = service.selectProductBest();
        List<productVO> hits = service.selectProductMode("hit");
        List<productVO> recommends = service.selectProductMode("review");
        List<productVO> news = service.selectProductMode("rdate");
        List<productVO> discounts = service.selectProductMode("discount");

        // 모델
        model.addAttribute("cate1s", cate1s);
        model.addAttribute("cate2s", cate2s);
        model.addAttribute("bests", bests);
        model.addAttribute("hits", hits);
        model.addAttribute("recommends", recommends);
        model.addAttribute("news", news);
        model.addAttribute("discounts", discounts);

        return "index";
    }

}
