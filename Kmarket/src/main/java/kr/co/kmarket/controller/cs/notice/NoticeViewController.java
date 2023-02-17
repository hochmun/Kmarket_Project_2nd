package kr.co.kmarket.controller.cs.notice;

import kr.co.kmarket.service.cs.CsNoticeService;
import kr.co.kmarket.vo.Cs_NoticeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class NoticeViewController {

    @Autowired
    private CsNoticeService service;

    @GetMapping(value = {"cs/notice/view"})
    public String view(Model model, String noCate, Integer no) {
        // noCate 값이 없을시 '전체'로 이동
        if (noCate == null || noCate.equals("")) {
            noCate = "%%";
        }

        // noCate 값을 정해진 값 이외의 값으로 입력시 에러페이지로 이동
        if (!noCate.equals("%%") && !noCate.equals("10") && !noCate.equals("11") && !noCate.equals("12") && !noCate.equals("13")) {
            return "alert";
        }

        Cs_NoticeVO vo = service.selectNotArticle(no);

        log.info("vo : " + vo);

        model.addAttribute("vo", vo);
        model.addAttribute("cate1", noCate);

        return "cs/notice/view";
    }
}
