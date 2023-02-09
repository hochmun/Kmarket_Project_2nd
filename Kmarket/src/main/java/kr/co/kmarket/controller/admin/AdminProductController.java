package kr.co.kmarket.controller.admin;

import kr.co.kmarket.util.PagingUtil;
import kr.co.kmarket.dto.PagingDTO;
import kr.co.kmarket.service.admin.AdminProductService;
import kr.co.kmarket.vo.productVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 관리자 상품 컨트롤러
 * @since 2023/02/09 // 심규영 // 최초 작성
 */
@Controller
public class AdminProductController {

    /**
     * @since 2023/02/09 // 심규영 // 관리자 상품 서비스 연결
     */
    @Autowired
    private AdminProductService service;

    /**
     * 관리자 상품 리스트 Get맵핑
     * @since 2023/02/09 // 심규영 // 최초 작성
     * @return
     */
    @GetMapping("admin/product/list/{pg}/")
    public String list(@PathVariable("pg") String pg,
                       Model model) {
        // 임시 아이디 설정
        String uid = "";

        // 페이징 처리
        PagingDTO paging = new PagingUtil().getPagingDTO(pg, service.selectCountProduct(uid));

        // 게시물 불러오기
        List<productVO> products = service.selectProducts(uid, paging.getStart());

        // 모델 전송
        model.addAttribute("paging", paging);
        model.addAttribute("products", products);

        // 리턴
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
