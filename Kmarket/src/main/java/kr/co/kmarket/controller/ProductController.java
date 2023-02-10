package kr.co.kmarket.controller;

import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.productVO;
import kr.co.kmarket.vo.product_cate2VO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    /**
     * 상품 list GetMapping
     * @since 23/02/09
     * @author 이해빈
     */
    @GetMapping("product/list")
    public String list(Model model, int cate1, int cate2, String sort, String pg){

        // 카테고리 이름 가져오기
        product_cate2VO cateName = service.getCateName(cate1, cate2);

        // 현재 페이지 번호
        int currentPage = service.getCurrentPage(pg);

        // 페이지 시작값
        int start = service.getLimitStart(currentPage);

        // 전체 게시물 갯수
        int total = service.getCountTotal(cate1, cate2);

        // 페이지 마지막 번호
        int lastPageNum = service.getLastPageNum(total);

        // 페이지 그룹 start, end 번호
        int[] pageGroup = service.getPageGroupNum(currentPage, lastPageNum);

        // 현재 페이지 상품 가져오기
        List<productVO> products = service.selectProducts(cate1, cate2, sort, start);

        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);
        model.addAttribute("cateName", cateName);
        model.addAttribute("sort", sort);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageGroup", pageGroup);

        return "product/list";
    }

    /**
     * 상품 view GetMapping
     * @since 23/02/10
     * @author 이해빈
     */
    @GetMapping("product/view")
    public String view(int cate1, int cate2, int prodNo, Model model){

        // 카테고리 이름 가져오기
        product_cate2VO cateName = service.getCateName(cate1, cate2);
        
        // 상품 가져오기
        productVO product = service.selectProduct(prodNo);

        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);
        model.addAttribute("cateName", cateName);
        model.addAttribute("product", product);


        return "product/view";
    }

    @GetMapping("product/order")
    public String order(){

        return "product/order";
    }

    @GetMapping("product/cart")
    public String cart(){

        return "product/cart";
    }

    @GetMapping("product/complete")
    public String complete(){

        return "product/complete";
    }

}
