package kr.co.kmarket.controller.cs.faq;

import kr.co.kmarket.service.cs.CsFaqService;
import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_Cate2VO;
import kr.co.kmarket.vo.Cs_FaqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FaqListController {

    @Autowired
    private CsFaqService service;

    @GetMapping(value = {"cs/faq/list"})
    public String list(Model model, String csCate1){
        // 넘어오는 cate1값 null = 10으로 초기화
        if(csCate1 == null || csCate1.equals("")){
            csCate1 = "10";
        }

        // 카테고리1 정보 가져오기
        List<Cs_Cate1VO> vos2 = service.selectCsCate1();

        // 카테1로 카테2 정보 가져오기
        List<Cs_Cate2VO> vos3 = service.selectCsCate2(csCate1);

        // cate1, cate2 값으로 카테고리 10개씩 가져오기
        List<Cs_FaqVO> vos = service.selectCsFaqListWithCsCate1();

        model.addAttribute("vos", vos);
        model.addAttribute("vos2", vos2);
        model.addAttribute("vos3", vos3);
        model.addAttribute("csCate1", csCate1);


        return "cs/faq/list";
    }
}
