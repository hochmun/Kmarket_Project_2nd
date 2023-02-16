package kr.co.kmarket.controller.cs.notice;

import kr.co.kmarket.service.cs.CsNoticeService;
import kr.co.kmarket.vo.Cs_NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeViewController {

    @Autowired
    private CsNoticeService service;

    @GetMapping(value = {"cs/notice/view"})
    public String view(Model model, Integer noticeNo, Integer noCate){
        Cs_NoticeVO vo = service.selectNotArticle(noticeNo);

        model.addAttribute("vo", vo);
        model.addAttribute("noCate", noCate);

        return "cs/notice/view";
    }
}
