package kr.co.kmarket.controller.cs.notice;

import kr.co.kmarket.service.cs.CsNoticeService;
import kr.co.kmarket.vo.Cs_NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NoticeViewController {

    @Autowired
    private CsNoticeService service;

    @GetMapping(value = {"cs/notice/view"})
    public String view(Model model, Integer noticeNo, String noCate){
        // noCate 값이 없을시 '전체'로 이동
        if (noCate == null || noCate.equals("")) {
            noCate = "%%";
        }

        // noCate 값을 정해진 값 이외의 값으로 입력시 에러페이지로 이동
        if (!noCate.equals("%%") && !noCate.equals("10") && !noCate.equals("11") && !noCate.equals("12") && !noCate.equals("13")) {
            return "alert";
        }

        // 카테고리별 게시물 리스트
        List<Cs_NoticeVO> vos = service.selectNotCate();

        // noticeNo로 게시물 상세보기
        Cs_NoticeVO vo = service.selectNotArticle(noticeNo);

        model.addAttribute("vos", vos);
        model.addAttribute("vo", vo);
        model.addAttribute("noticeNo", noticeNo);
        model.addAttribute("cate1", noCate);

        return "cs/notice/view";
    }
}
