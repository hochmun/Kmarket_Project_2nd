package kr.co.kmarket.controller.cs.qna;

import kr.co.kmarket.service.cs.CsQnaService;
import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_Cate2VO;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class QnaViewController {

    @Autowired
    private CsQnaService service;

    @GetMapping(value = {"cs/qna/view"})
    public String view(Model model, Integer qnaNo, Integer cate1){

        Cs_Cate1VO vos = service.selectCate1Name(cate1);

        Cs_QnaVO vo = service.selectQnaArticle(qnaNo);

        model.addAttribute("vo", vo);
        model.addAttribute("cate1", cate1);

        return "cs/qna/view";
    }

}
