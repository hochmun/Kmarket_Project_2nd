package kr.co.kmarket.controller.admin;

import kr.co.kmarket.dto.AdminCsListParamDTO;
import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.admin.AdminCsService;
import kr.co.kmarket.vo.AdminCsVo;
import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_Cate2VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
     * 자주묻는 질문, 공지사항 (faq, notice)
     * 들어오는 정보
     *      no => 게시물 번호
     *      pg => 게시물이 있던 페이지 번호
     * @since 2023/02/09 // 심규영
     * @param cate
     * @return
     */
    @GetMapping("admin/cs/{cate}/modify")
    public String modify(@PathVariable("cate") String cate,
                         @RequestParam Map<String, String> etcText,
                         Model model,
                         @AuthenticationPrincipal MyUserDetails myUserDetails) {
        // 데이터를 담을 Map 생성
        Map<String, Object> data = new HashMap<>();
        data.put("etcText", etcText);
        
        // 게시물 불러오기
        AdminCsVo article = service.selectCsArticle(etcText, cate);
        data.put("article", article);
        
        // ca|te가 faq일 경우 cs_cate1, cs_cate2 리스트 불러오기
        if(cate.equals("faq")){
            List<Cs_Cate1VO> cate1VOs = service.selectCsCate1s();
            List<Cs_Cate2VO> cate2VOs = service.selectCsCate2sWithCate1(article.getFaqCate2()+"");
            data.put("cate1VOs", cate1VOs);
            data.put("cate2VOs", cate2VOs);
        }

        // 모델 전송
        model.addAttribute("name", myUserDetails.getUser().getName());
        model.addAttribute("cate", cate);
        model.addAttribute("data", data);

        return "admin/cs/modify";
    }

    /**
     * 2023/02/17 // 심규영 // 관리자 고객센터 수정 포스트 처리
     * 들어오는 값
     *      no          => 수정할 게시물 번호
     *      title       => 수정된 게시글 제목
     *      content     => 수정된 게시글 내용
     *      // if cate==faq
     *          cate1   => 수정된 게시글 카테고리1
     *          cate2   => 수정된 게시글 카테고리2
     *      // else if cate==notice
     *          type    => 수정된 공지사항 카테고리
     */
    @ResponseBody
    @PostMapping("admin/cs/{cate}/modify")
    public Map<String, Object> modify(@PathVariable("cate") String cate,
                                      @RequestBody Map<String, String> etcText) {
        // 데이터를 담아 리턴할 맵 생성
        Map<String, Object> data = new HashMap<>();

        // 결과 값
        int result = service.CsModifyPostProcess(cate, etcText);
        data.put("result", result);

        return data;
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
                       Model model,
                       @RequestParam Map<String, String> map,
                       @AuthenticationPrincipal MyUserDetails myUserDetails) {
        // 데이터를 담을 map
        Map<String, Object> data = new HashMap<>();
        data.put("etcText", map);

        // 게시물 불러오기
        AdminCsVo article = service.selectCsArticle(map, cate);
        data.put("article", article);
        
        // 모델 전송
        model.addAttribute("name", myUserDetails.getUser().getName());
        model.addAttribute("cate", cate);
        model.addAttribute("data", data);

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
                        Model model,
                        @AuthenticationPrincipal MyUserDetails myUserDetails) {
        // 종합 처리 메소드
        // 포함 할 수 있는 내용
        //      cate1VOs => cs_cate1 리스트
        Map<String, Object> data = service.CsWriteDataProcess(cate);

        model.addAttribute("name", myUserDetails.getUser().getName());
        model.addAttribute("cate", cate);
        model.addAttribute("data", data);

        return "admin/cs/write";
    }

    /**
     * 2023/02/16 // 심규영 // 글작성 포스트 처리
     * map에 들어올 수 있는 내용
     *
     */
    @ResponseBody
    @PostMapping("admin/cs/{cate}/write")
    public Map<String, Object> write(@RequestBody Map<String, String> map,
                                     @PathVariable("cate") String cate,
                                     HttpServletRequest req) {
        map.put("regip", req.getRemoteAddr());
        int result = service.CsWritePostProcess(map, cate);

        Map<String, Object> data = new HashMap<>();
        data.put("result", result);

        return data;
    }

    /**
     * 관리자 고객센터 답변 페이지
     * 들어오는 정보
     *      no => 페이지 번호
     *      pg => 게시물이 있던 페이지
     *      cate1 => 게시물이 있던 카테고리1 번호
     *      cate2 => 게시물이 있던 카테고리2 번호
     * @since 2023/02/09 // 심규영
     * @param cate
     * @return
     */
    @GetMapping("admin/cs/{cate}/reply")
    public String reply(@PathVariable("cate") String cate,
                        Model model,
                        @RequestParam Map<String, String> map,
                        @AuthenticationPrincipal MyUserDetails myUserDetails) {
        // 정보를 담을 map 생성
        Map<String, Object> data = new HashMap<>();
        data.put("etcText", map);

        // 해당 글 정보 가져오기
        AdminCsVo article = service.selectCsArticle(map, cate);
        data.put("article", article);

        // 모델 전송
        model.addAttribute("name", myUserDetails.getUser().getName());
        model.addAttribute("cate", cate);
        model.addAttribute("data", data);


        return "admin/cs/reply";
    }

    /**
     * 2023/02/16 // 심규영 // 관리자 고객센터 문의하기 답변 Post 처리
     * map에 들어오는 값
     *      no                  => 게시물 번호
     *      qnaAdminContent     => 관리자가 답변한 답변 내용
     */
    @ResponseBody
    @PostMapping("admin/cs/{cate}/reply")
    public Map<String, Object> reply(@RequestBody Map<String, String> map) {
        // 결과값 선언
        int result = 0;

        // 데이터 베이스에 답변 내용 넣기
        result = service.updateQnaArticle(map.get("qnaAdminContent"), map.get("no"));

        // 결과값 담을 map
        Map<String, Object> data = new HashMap<>();
        data.put("result", result);
        
        // 리턴
        return data;
    }

    /**
     * 2023/02/16 // 심규영 // 관리자 고객센터 게시글 삭제
     * map에 들어오는 값
     *       no => 게시글 번호
     */
    @ResponseBody
    @PostMapping("admin/cs/{cate}/delete")
    public Map<String, Object> articleDelete(@RequestBody Map<String, String> map,
                              @PathVariable("cate") String cate) {
        // 결과값
        int result = 0;

        // 게시물 번호 받기
        // 게시물 삭제 여러개 대처용
        String[] nos = map.get("no").split(",");
        
        // 게시물 삭제 하기
        for(String no : nos) {
            result += service.deleteCsArticle(no, cate);
        }

        // 리턴하는 map 생성
        Map<String, Object> data = new HashMap<>();
        data.put("result", result);

        // 리턴
        return data;
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
