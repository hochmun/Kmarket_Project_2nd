package kr.co.kmarket.controller.cs.qna;

import kr.co.kmarket.service.cs.CsQnaService;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QnaViewController {

    @Autowired
    private CsQnaService service;

    @GetMapping(value = {"cs/qna/view"})
    public String view(Model model, String group, String pg, String cate1, Integer qnaNo){
        // null값 처리
        pg = (pg == null) ? "1" : pg;
        group = (group == null) ? "1" : group;

        // 페이지 정보
        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        long total = service.getTotalCount(cate1);
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        // 게시물 불러오기
        Cs_QnaVO vo = service.selectQnaArticle(qnaNo);

        model.addAttribute("group", group);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("total", total);
        model.addAttribute("start", start);

        model.addAttribute("vo", vo);
        model.addAttribute("cate1", cate1);

        return "cs/qna/view";
    }
}
