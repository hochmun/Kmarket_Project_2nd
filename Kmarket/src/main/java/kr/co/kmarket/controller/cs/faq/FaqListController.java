package kr.co.kmarket.controller.cs.faq;

import kr.co.kmarket.service.cs.CsFaqService;
import kr.co.kmarket.service.cs.CsQnaService;
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

    @Autowired
    private CsQnaService qnaService;

    @GetMapping(value = {"cs/faq/list"})
    public String list(Model model, Integer cate1, List<Cs_Cate2VO> vos){

        Cs_Cate1VO vo = qnaService.selectCate1Name(cate1);

        List<Cs_FaqVO> fvos = service.selectCsFaqListWithCsCate1(vos);

        model.addAttribute("fvos", fvos);
        model.addAttribute("vo", vo);
        model.addAttribute("cate1", cate1);

        return "cs/faq/list";
    }
}
