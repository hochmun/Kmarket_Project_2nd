package kr.co.kmarket.controller.cs;

import kr.co.kmarket.service.cs.CsIndexService;
import kr.co.kmarket.service.cs.CsQnaService;
import kr.co.kmarket.vo.Cs_NoticeVO;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CsIndexController {
    @Autowired
    private CsIndexService service;

    @GetMapping(value = {"cs/index"})

    public String index(Model model){
        List<Cs_NoticeVO> vos1 = service.selectCsNoticeListLimit5();

        List<Cs_QnaVO> vos2 = service.selectCsQnaListLimit5();

        model.addAttribute("vos1", vos1);
        model.addAttribute("vos2", vos2);

        return "cs/index";
    }
}