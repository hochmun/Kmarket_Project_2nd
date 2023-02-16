package kr.co.kmarket.controller.cs.notice;

import kr.co.kmarket.service.cs.CsNoticeService;
import kr.co.kmarket.vo.Cs_NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NoticeListController {

    @Autowired
    private CsNoticeService service;

    @GetMapping(value = {"cs/notice/list"})
    public String list(Model model, String noCate, String pg){
        pg = (pg == null) ? "1" : pg;

        // noCate 값이 없을시 '전체'로 이동
        if (noCate == null || noCate.equals("")) {
            noCate = "%%";
        }

        // noCate 값을 정해진 값 이외의 값으로 입력시 에러페이지로 이동
        if (!noCate.equals("%%") && !noCate.equals("10") && !noCate.equals("11") && !noCate.equals("12") && !noCate.equals("13")) {
            return "alert";
        }

        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        long total = service.getTotalCount(noCate);
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        // 공지사항 글 전체 불러오기
        List<Cs_NoticeVO> NotArts = service.selectNotArticlesAll(start);

        // 공지사항 글 카테고리별 불러오기
        List<Cs_NoticeVO> vos = service.selectNotArticles(start, noCate);

        model.addAttribute("NotArts", NotArts);
        model.addAttribute("vos", vos);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("cate1", noCate);

        return "cs/notice/list";
    }
}
