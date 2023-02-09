package kr.co.kmarket.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 2023/02/09 // 심규영 // 관리자 고객센터 컨트롤러
 */
@Controller
public class AdminCsController {

    /**
     * 관리자 고객센터 목록 페이지
     * @since 2023/02/09 // 심규영
     * @param cate
     * @return
     */
    @GetMapping("admin/cs/{cate}/list")
    public String list(@PathVariable("cate") String cate,
                       Model model) {
        model.addAttribute("cate", cate);

        return "admin/cs/list";
    }

    /**
     * 관리자 고객센터 수정 페이지
     * @since 2023/02/09 // 심규영
     * @param cate
     * @return
     */
    @GetMapping("admin/cs/{cate}/modify")
    public String modify(@PathVariable("cate") String cate) {
        return "admin/cs/modify";
    }

    /**
     * 관리자 고객센터 보기 페이지
     * @since 2023/02/09 // 심규영
     * @param cate
     * @return
     */
    @GetMapping("admin/cs/{cate}/view")
    public String view(@PathVariable("cate") String cate) {
        return "admin/cs/view";
    }

    /**
     * 관리자 고객센터 글 쓰기 페이지
     * @since 2023/02/09 // 심규영
     * @param cate
     * @return
     */
    @GetMapping("admin/cs/{cate}/write")
    public String write(@PathVariable("cate") String cate) {
        return "admin/cs/write";
    }

    /**
     * 관리자 고객센터 답변 페이지
     * @since 2023/02/09 // 심규영
     * @param cate
     * @return
     */
    @GetMapping("admin/cs/{cate}/reply")
    public String reply(@PathVariable("cate") String cate) {
        return "admin/cs/reply";
    }

}
