package kr.co.kmarket.controller.cs.qna;

import kr.co.kmarket.service.cs.CsQnaService;
import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class QnaListController {

    @Autowired
    private CsQnaService service;

    @GetMapping(value = {"cs/qna/list"})
    public String list(Model model, String group, String pg, HttpServletRequest req){
        // null값 처리
        pg = (pg == null) ? "1" : pg;
        group = (group == null) ? "1" : group;

        String cate1 = req.getParameter("cate1");

        // 페이지 정보
        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        long total = service.getTotalCount(cate1);
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        // 게시물 불러오기
        Cs_Cate1VO vos = service.selectCate1(cate1);
        List<Cs_QnaVO> QnaArts = service.selectQnaArticles(start, cate1);

        model.addAttribute("group", group);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("total", total);
        model.addAttribute("start", start);

        model.addAttribute("cate1", cate1);
        model.addAttribute("vos", vos);
        model.addAttribute("QnaArts", QnaArts);

        return "cs/qna/list";
    }

}
