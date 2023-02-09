package kr.co.kmarket.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 관리자 상품 컨트롤러
 * @since 2023/02/09 // 심규영 // 최초 작성
 */
@Controller
public class AdminProductController {

    /**
     * 관리자 상품 리스트 Get맵핑
     * @since 2023/02/09 // 심규영 // 최초 작성
     * @return
     */
    @GetMapping("admin/product/list")
    public String list() {
        return "admin/product/list";
    }

    /**
     * 관리자 상품 등록 페이지 Get맵핑
     * @since 2023/02/09 // 심규영 // 최초 작성
     * @return
     */
    @GetMapping("admin/product/register")
    public String register() {
        return "admin/product/register";
    }

}
