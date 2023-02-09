package kr.co.kmarket.controller.cs.qna;

import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.cs.CsQnaService;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class QnaWriteController {

    @Autowired
    private CsQnaService service;

    @GetMapping(value = {"cs/qna/write"})
    public String write(Model model, String qnaCate1){
        model.addAttribute("qnaCate1", qnaCate1);
        return "cs/qna/write";
    }

    @PostMapping(value = {"cs/qna/write"})
    public String write(Model model, String qnaCate1, String qnaCate2, Cs_QnaVO vo, HttpServletRequest req, @AuthenticationPrincipal MyUserDetails myUser){
        vo.setUid(myUser.getUser().getUid());
        vo.setQnaRegip(req.getRemoteAddr());
        int result = service.insertQnaArticle(vo);
        if(result > 0){
            return "redirect:cs/qna/list(cate${qnaCate1})";
        }

        model.addAttribute("qnaCate1", qnaCate1);
        model.addAttribute("qnaCate2", qnaCate2);
        return "redirect:cs/qna/list";
    }
}
