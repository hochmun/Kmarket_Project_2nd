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
    public String list(Model model, Integer noCate, String pg){
        pg = (pg == null) ? "1" : pg;

        if (noCate == null) {
            noCate = 10;
            return "redirect:/cs/notice/list?noCate=10";
        }

        // nocate < 13이면 "페이지가 없습니다." 페이지 이동 없음.
        if (noCate < 13) {
            return "cs/notice/list";
        }

        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        long total = service.getTotalCount();
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        List<Cs_NoticeVO> vos = service.selectNotArticles(start);

        model.addAttribute("vos", vos);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("noCate", noCate);

        return "cs/notice/list";
    }
}
