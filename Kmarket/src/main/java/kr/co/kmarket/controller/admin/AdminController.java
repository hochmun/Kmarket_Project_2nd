package kr.co.kmarket.controller.admin;

import kr.co.kmarket.security.MyUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 관리자 메인 컨트롤러
 * @since 2023/02/09 심규영
 */
@Controller
public class AdminController {

    /**
     * 관리자 메인 페이지 Get맵핑
     * @since 2023/02/09 심규영
     * @return
     */
    @GetMapping("admin/index")
    public String index(@AuthenticationPrincipal MyUserDetails myUserDetails,
                        Model model) {
        String name = myUserDetails.getUser().getName();

        model.addAttribute("name", name);

        return "admin/index";
    }

}
