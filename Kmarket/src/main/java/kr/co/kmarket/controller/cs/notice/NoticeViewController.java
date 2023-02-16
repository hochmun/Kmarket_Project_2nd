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

    public String view(Model model, Integer noticeNo, String noCate1){

        // noCate 값이 없을시 '전체'로 이동
        if (noCate1 == null || noCate1.equals("")) {
            noCate1 = "";
        }

        // noCate 값을 정해진 값 이외의 값으로 입력시 에러페이지로 이동
        if (!noCate1.equals("") && !noCate1.equals("10") && !noCate1.equals("11") && !noCate1.equals("12") && !noCate1.equals("13")) {
            return "alert";
        }

        Cs_NoticeVO vo = service.selectNotArticle(noticeNo);

        model.addAttribute("vo", vo);
        model.addAttribute("cate1", noCate1);

        return "cs/notice/view";
    }
}
