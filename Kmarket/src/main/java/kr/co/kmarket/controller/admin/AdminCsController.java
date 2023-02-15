package kr.co.kmarket.controller.admin;

import kr.co.kmarket.dto.AdminCsListParamDTO;
import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.admin.AdminCsService;
import kr.co.kmarket.vo.Cs_Cate2VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2023/02/09 // 심규영 // 관리자 고객센터 컨트롤러
 */
@Controller
public class AdminCsController {

    /**
     * 2023/02/14 // 심규영 // 서비스 연결
     */
    @Autowired
    private AdminCsService service;
    
    /**
     * 관리자 고객센터 목록 페이지
     * 들어올 가능성이 있는 정보
     *      cate1 => cs_cate1 번호
     *      cate2 => cs_cate2 번호
     *      type => notice cate 번호
     *      pg => 페이지 번호
     * @since 2023/02/09 // 심규영
     * @param cate
     * @return
     */
    @GetMapping("admin/cs/{cate}/list")
    public String list(@PathVariable("cate") String cate,
                       @ModelAttribute("parameter") AdminCsListParamDTO param,
                       Model model,
                       @AuthenticationPrincipal MyUserDetails myUserDetails) {
        // 리스트 페이지 종합 처리
        // 맵에 넣어서 관리 편하게 하기 위해서
        Map<String, Object> data = service.CsListDataProcess(cate, param);

        model.addAttribute("cate", cate);
        model.addAttribute("name", myUserDetails.getUser().getName());
        model.addAttribute("data", data);

        return "admin/cs/list";
    }

    /**
     * 관리자 고객센터 수정 페이지
     * 자주묻는 질문, 공지사항
     * 들어오는 정보
     *      no => 게시물 번호
     * @since 2023/02/09 // 심규영
     * @param cate
     * @return
     */
    @GetMapping("admin/cs/{cate}/modify")
    public String modify(@PathVariable("cate") String cate,
                         Model model) {
        model.addAttribute("cate", cate);

        return "admin/cs/modify";
    }

    /**
     * 관리자 고객센터 보기 페이지
     * 들어올수 있는 정보
     *      no => 게시물 번호
     *      cate1 => cs_cate1 번호
     *      cate2 => cs_cate2 번호
     *      type => notice cate 번호
     *      pg => 게시물이 있던 페이지 번호
     * @since 2023/02/09 // 심규영
     * @param cate
     * @return
     */
    @GetMapping("admin/cs/{cate}/view")
    public String view(@PathVariable("cate") String cate,
                       Model model) {
        model.addAttribute("cate", cate);

        return "admin/cs/view";
    }

    /**
     * 관리자 고객센터 글 쓰기 페이지
     * @since 2023/02/09 // 심규영
     * @param cate
     * @return
     */
    @GetMapping("admin/cs/{cate}/write")
    public String write(@PathVariable("cate") String cate,
                        Model model) {
        model.addAttribute("cate", cate);

        return "admin/cs/write";
    }

    /**
     * 관리자 고객센터 답변 페이지
     * 들어오는 정보
     *      no => 페이지 번호
     *      pg => 게시물이 있던 페이지
     * @since 2023/02/09 // 심규영
     * @param cate
     * @return
     */
    @GetMapping("admin/cs/{cate}/reply")
    public String reply(@PathVariable("cate") String cate,
                        Model model) {
        model.addAttribute("cate", cate);

        return "admin/cs/reply";
    }

    /**
     * 2023/02/15 // 심규영 // 고객센터 1값 변경시 카테고리2 리스트 반환
     */
    @ResponseBody
    @PostMapping("admin/cs/getCsCate2")
    public Map<String, Object> getCsCate2(@RequestBody Map<String, String> map) {
         List<Cs_Cate2VO> cate2VOs = service.selectCsCate2sWithCate1(map.get("cate1"));

         Map<String, Object> data = new HashMap<>();
         data.put("cate2VOs", cate2VOs);

         return data;
    }


}
