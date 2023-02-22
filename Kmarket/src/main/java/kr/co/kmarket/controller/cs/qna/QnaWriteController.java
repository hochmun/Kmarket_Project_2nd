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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class QnaWriteController {

    @Autowired
    private CsQnaService service;

    /**
     * 23/02/09 Q&A 글쓰기 처리 컨트롤러 메서드
     * @author 김재준
     * @param model
     * @return
     */
    @GetMapping(value = {"cs/qna/write"})
    public String write(Model model, @RequestParam("cate1") String cate1){

        model.addAttribute("cate1", cate1);

        return "cs/qna/write";
    }

    /**
     * 23/02/09 Q&A 글쓰기 처리 컨트롤러 메서드
     * @author 김재준
     * @param model
     * @param req
     * @param myUser
     * @param vo
     * @return
     */
    @PostMapping(value = {"cs/qna/write"})
    public String write(Model model, HttpServletRequest req, @AuthenticationPrincipal MyUserDetails myUser, Cs_QnaVO vo,
                        Integer cate1, Integer cate2){

        UserEntity user = myUser.getUser();

        vo.setUid(user.getUid());
        if (cate2 == null || cate2 == 0) {
            model.addAttribute("errorMessage", "카테고리를 선택하세요.");
            return "cs/qna/write";
        }
        vo.setQnaCate1(String.valueOf(cate1));
        vo.setQnaCate2(String.valueOf(cate2));
        vo.setQnaRegip(req.getRemoteAddr());
        service.insertQnaArticle(vo);

        return "redirect:/cs/qna/list?cate1="+cate1;
    }
}
