package kr.co.kmarket.controller.admin;

import kr.co.kmarket.dto.AdminMainStatusDTO;
import kr.co.kmarket.entity.UserEntity;
import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.admin.AdminService;
import kr.co.kmarket.vo.Cs_NoticeVO;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 관리자 메인 컨트롤러
 * @since 2023/02/09 심규영
 */
@Controller
public class AdminController {

    /**
     * 2023/02/20 // 심규영 // 관리자 메인 서비스 연결
     */
    @Autowired
    private AdminService service;

    /**
     * 관리자 메인 페이지 Get맵핑
     * @since 2023/02/09 심규영
     * @return
     */
    @GetMapping("admin/index")
    public String index(@AuthenticationPrincipal MyUserDetails myUserDetails,
                        Model model) {
        // 유저 정보 가져오기
        UserEntity user = myUserDetails.getUser();

        // 쇼핑몰 운영 현황 가져오기
        // 관리자일 경우에만
        // 판매자는 공개 하지 안음
        if(user.getType() == 5) {
            AdminMainStatusDTO mainStatusDTO = service.selectAdminMainStatus();
            model.addAttribute("mainStatusDTO", mainStatusDTO);
        }

        // 공지사항 게시물 5개 가져오기
        List<Cs_NoticeVO> noticeList = service.selectNoticeArticle5();

        // 고객문의 게시물 5개 가져오기
        List<Cs_QnaVO> qnaList = service.selectQnaArticle5();

        // 모델 전송
        model.addAttribute("name", user.getName());
        model.addAttribute("noticeVOs", noticeList);
        model.addAttribute("qnaVOs", qnaList);
    
        // 리턴
        return "admin/index";
    }

}
