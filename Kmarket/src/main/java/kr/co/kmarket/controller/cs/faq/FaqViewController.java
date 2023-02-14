package kr.co.kmarket.controller.cs.faq;

import kr.co.kmarket.service.cs.CsFaqService;
import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_FaqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FaqViewController {

    @Autowired
    private CsFaqService service;

    @GetMapping(value = {"cs/faq/view"})
    public String view(Model model, Integer faqNo){

        // 카테고리1 정보 가져오기
        List<Cs_Cate1VO> vos2 = service.selectCsCate1();

        // faqNo로 게시물 상세보기
        Cs_FaqVO vo = service.selectCsFaqWithFaqNo(faqNo);

        model.addAttribute("vos2", vos2);
        model.addAttribute("vo", vo);
        model.addAttribute("faqNo", faqNo);
        model.addAttribute("csCate1", vo.getFaqCate1());

        return "cs/faq/view";
    }
}
