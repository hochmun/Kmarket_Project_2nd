package kr.co.kmarket.controller.cs.qna;

import kr.co.kmarket.entity.UserEntity;
import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.cs.CsQnaService;
import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_QnaVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@Slf4j
public class QnaListController {

    @Autowired
    private CsQnaService service;

    /**
     * 23/02/09 Q&A 리스트 컨트롤러 메서드
     * @author 김재준
     * @return
     */
    @GetMapping(value = {"cs/qna/list"})
        public String list(Model model, String pg, Integer cate1){

        pg = (pg == null) ? "1" : pg;
        if (cate1 == null) {
            cate1 = 10;
            return "redirect:/cs/qna/list?cate1=10";
        }

        int currentPage = service.getCurrentPage(pg);
        int start = service.getLimitStart(currentPage);
        long total = service.getTotalCount(cate1);
        int lastPage = service.getLastPageNum(total);
        int pageStartNum = service.getPageStartNum(total, start);
        int groups[] = service.getPageGroup(currentPage, lastPage);

        List<Cs_QnaVO> QnaArts = service.selectQnaArticles(start, cate1);

        Cs_Cate1VO vos = service.selectCate1Name(cate1);

        model.addAttribute("QnaArts", QnaArts);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageStartNum", pageStartNum);
        model.addAttribute("groups", groups);
        model.addAttribute("vos", vos);
        model.addAttribute("cate1", cate1);

        return "cs/qna/list";
    }
}
