package kr.co.kmarket.controller.admin;

import kr.co.kmarket.service.MainService;
import kr.co.kmarket.util.PagingUtil;
import kr.co.kmarket.dto.PagingDTO;
import kr.co.kmarket.service.admin.AdminProductService;
import kr.co.kmarket.vo.productVO;
import kr.co.kmarket.vo.product_cate1VO;
import kr.co.kmarket.vo.product_cate2VO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 관리자 상품 컨트롤러
 * @since 2023/02/09 // 심규영 // 최초 작성
 */
@Slf4j
@Controller
public class AdminProductController {

    /**
     * @since 2023/02/09 // 심규영 // 관리자 상품 서비스 연결
     */
    @Autowired
    private AdminProductService service;

    @Autowired
    private MainService mainService;

    /**
     * 관리자 상품 리스트 Get맵핑
     * @since 2023/02/09 // 심규영 // 최초 작성
     * @since 2023/02/10 // 심규영 // 검색 기능 추가
     * @return
     */
    @GetMapping("admin/product/list/{pg}/")
    public String list(@PathVariable("pg") String pg,
                       Model model,
                       @RequestParam(value = "s", required = false,defaultValue = "") String s,
                       @RequestParam(value = "st", required = false,defaultValue = "") String st) {
        // 임시 아이디 설정
        String uid = "";

        // 페이징 처리
        PagingDTO paging = new PagingUtil().getPagingDTO(pg, service.selectCountProduct(uid, s, st));

        // 게시물 불러오기
        List<productVO> products = service.selectProducts(uid, paging.getStart(), s, st);

        // 모델 전송
        model.addAttribute("s", s);
        model.addAttribute("st", st);
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
    public String register(Model model) {
        List<product_cate1VO> cate1s = mainService.selectCate1s();

        model.addAttribute("cate1s", cate1s);

        return "admin/product/register";
    }

    /**
     * 카테고리 1값에 따른 
     * 2023/02/10 // 심규영 // 최초작성
     * @param cate1
     * @param model
     */
    @GetMapping("admin/product/getcate2")
    public void getCate2(@RequestParam("cate1") String cate1,
                         Model model) {
        List<product_cate2VO> cate2s = mainService.selectCate2WithCate1(cate1);

        model.addAttribute("cate2s", cate2s);
    }

}
