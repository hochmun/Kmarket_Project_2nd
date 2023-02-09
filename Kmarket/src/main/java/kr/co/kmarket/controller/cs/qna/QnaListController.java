package kr.co.kmarket.controller.cs.qna;

import kr.co.kmarket.entity.UserEntity;
import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.cs.CsQnaService;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class QnaListController {

    @Autowired
    private CsQnaService service;

    @GetMapping(value = {"cs/qna/list"})
    public String list(Model model, String cate1, String group, String pg){
        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        long total = service.getTotalCount();
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        List<Cs_QnaVO> QnaArts = service.selectQnaArticle(start);

        model.addAttribute("group", group);
        model.addAttribute("cate1", cate1);
        model.addAttribute("QnaArts", QnaArts);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);

        return "cs/qna/list";
    }
}
