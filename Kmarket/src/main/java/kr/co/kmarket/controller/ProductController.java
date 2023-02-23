package kr.co.kmarket.controller;

import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.dto.SearchDTO;
import kr.co.kmarket.entity.UserEntity;
import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Slf4j
@Controller
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private MemberService memberservice;

    /**
     * 상품 list GetMapping
     * @since 23/02/09
     * @author 이해빈
     *
     * 2023/02/17 상품 전체 list 불러오기 기능
     */
    @GetMapping("product/list")
    public String list(Model model, @RequestParam(value="cate1", required = false, defaultValue = "0") int cate1,
                                    @RequestParam(value="cate2", required = false, defaultValue = "0") int cate2,
                                    @RequestParam(value="search", required = false) String search, String sort, String pg){


        product_cate2VO cateName = null;

        // 메인에서 검색해서 들어온 경우가 아닌 겨우
        if(cate1 != 0){
            // 카테고리 이름 가져오기
            cateName = service.getCateName(cate1, cate2);
        }else{ // 메인에서 검색해 들어온 경우
            if(sort == null){
                sort = "sold";
            }
        }

        // 게시글 출력 갯수
        int count = 10;

        // 현재 페이지 번호
        int currentPage = service.getCurrentPage(pg);

        // 페이지 시작값
        int start = service.getLimitStart(currentPage, count);

        // 전체 게시물 갯수
        int total = service.getCountTotal(cate1, cate2, search);

        // 페이지 마지막 번호
        int lastPageNum = service.getLastPageNum(total, count);

        // 페이지 그룹 start, end 번호
        int[] pageGroup = service.getPageGroupNum(currentPage, lastPageNum);

        // 현재 페이지 상품 가져오기
        List<productVO> products = service.selectProducts(cate1, cate2, sort, start, search);

        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);
        model.addAttribute("cateName", cateName);
        model.addAttribute("sort", sort);
        model.addAttribute("search", search);

        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageGroup", pageGroup);

        return "product/list";
    }

    /**
     * 상품 view GetMapping
     * @since 23/02/10, 
     * @author 이해빈
     * 
     * 23/02/16 상품 리뷰보기
     */
    @GetMapping("product/view")
    public String view(int cate1, int cate2, int prodNo, Model model, String pg){

        // 카테고리 이름 가져오기
        product_cate2VO cateName = service.getCateName(cate1, cate2);
        
        // 상품 가져오기
        productVO product = service.selectProduct(prodNo);


        // 리뷰쪽 페이징

        int count = 5; // 출력갯수
        int currentPage = service.getCurrentPage(pg); // 현재 페이지
        int start = service.getLimitStart(currentPage, count); // 페이지 시작값
        int total = service.getCountTotalForReview(prodNo); // 전체 게시물 갯수
        int lastPageNum = service.getLastPageNum(total, count); // 페이지 마지막 번호
        int[] pageGroup = service.getPageGroupNum(currentPage, lastPageNum); // 페이지 그룹 start, end

        // 리뷰 가져오기
        List<product_reviewVO> reviews = service.selectReviews(prodNo, start);

        model.addAttribute("cate1", cate1);
        model.addAttribute("cate2", cate2);
        model.addAttribute("cateName", cateName);
        model.addAttribute("product", product);
        model.addAttribute("reviews", reviews);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageGroup", pageGroup);

        return "product/view";
    }

    /**
     * 상품 order GetMapping
     * @since 23/02/10
     * @author 이해빈
     * 
     * 23/02/14 상품 목록 가져오기
     */
    @GetMapping("product/order")
    public String order(@AuthenticationPrincipal MyUserDetails myUser, Model model, HttpSession session){

        String uid = myUser.getUser().getUid();

        // 유저 정보가져오기
        memberVO member = memberservice.selectMember(uid);

        List<CartDTO> cdtos = null;

        // 상품 view 페이지에서 주문하기로 바로 넘어오는 경우
        if(session.getAttribute("prodNo") != null) {

            CartDTO prodNo = (CartDTO) session.getAttribute("prodNo");

            // 세션에 저장된 carts 값 제거
            //session.removeAttribute("prodNo"); --> 여기서 세션에 저장된 prodNo값을 지울 경우 새로고침을 했을 때 목록이 날아감
            session.removeAttribute("carts");

            cdtos = new ArrayList<>();
            cdtos.add(prodNo);

        }

        // 상품 carts 페이지에서 넘어오는 경우
        if(session.getAttribute("carts") != null) {

            cdtos = (List<CartDTO>) session.getAttribute("carts");

            // 세션에 저장된 prodNo 값 제거
            //session.removeAttribute("carts");
            session.removeAttribute("prodNo");
        }

        model.addAttribute("member", member);
        model.addAttribute("cdtos", cdtos);

        return "product/order";
    }

    /**
     * 상품 주문하기 기능
     * @since 23/02/15
     * @author 이해빈
     * */
    @ResponseBody
    @PostMapping("product/order")
    public Map<String, Integer> order(@RequestBody HashMap<String, Object> requestBody, @AuthenticationPrincipal MyUserDetails myUser, HttpSession session){

        // 주문번호
        int result = 0;

        List<String> cartNos = (List<String>) requestBody.get("checkboxArr");
        Map<String, Object> orderinfo = (Map<String, Object>) requestBody.get("orderinfo");

        // 유저정보 가져오기
        String uid = myUser.getUser().getUid();
        orderinfo.put("ordUid", uid);

        // DB 업데이트
        int ordNo = service.updateOrder(cartNos, orderinfo);

        // 세션에 주문한 상품 저장
        if(ordNo > 0){
            result = 1;
            session.setAttribute("ordNo", ordNo);

        }

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }

    /**
     * 장바구니 목록
     * @since 23/02/13
     * @author 이해빈
     */
    @GetMapping("product/cart")
    public String cart(Model model, @AuthenticationPrincipal MyUserDetails myUser){

        String uid = myUser.getUser().getUid();

        // 장바구니 목록 가져오기
        List<CartDTO> carts = service.selectCarts(uid);

        model.addAttribute("carts", carts);

        return "product/cart";
    }


    /**
     * 구매 완료 페이지
     * @since 23/02/15
     * @author 이해빈
     */
    @GetMapping("product/complete")
    public String complete(Model model, HttpSession session){


        // 세션에 주문번호 값이 없을 경우 메인으로 return
        if(session.getAttribute("ordNo") == null){
            return "redirect:/index";
        }

        // 세션에 저장된 주문번호 가져오기
        int ordNo = (int) session.getAttribute("ordNo");

        //세션에서 주문번호 삭제
        session.removeAttribute("ordNo");

        // 주문 내용 가져오기
        product_orderVO orderinfo = service.selectOrder(ordNo);

        // 주문한 상품 목록 가져오기
        List<product_order_itemVO> items = service.selectOrderItems(ordNo);

        model.addAttribute("orderinfo", orderinfo);
        model.addAttribute("items", items);

        return "product/complete";
    }

    /**
     * 장바구니 담기 + 주문하기 페이지 이동
     * @since 23/02/13
     * @author 이해빈
     */
    @ResponseBody
    @PostMapping("product/goToOrder")
    public Map<String , Integer> goToOrder(@RequestBody CartDTO cart, @AuthenticationPrincipal MyUserDetails myUser, HttpSession session){

        int result = 0;
        
        String type = cart.getType();

        // 장바구니에 담을 경우
        if(type.equals("cart")){
            UserEntity user = myUser.getUser();
            cart.setUid(user.getUid());
            result = service.addCart(cart);

        
        // 주문하기 페이지로 이동하는 경우 -> 세션에 저장
        }else if(type.equals("product")){
            if(cart.getProdNo() > 0){
                result = 1;
            }
            
            // 세션에 값이 있는 경우 초기화 후 저장
            Object prodNo = session.getAttribute("prodNo");

            if (prodNo != null) {
                session.removeAttribute("prodNo");
            }
            session.setAttribute("prodNo", cart);

        }

        Map<String , Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }

    /**
     * 장바구니 선택 삭제
     * @since 23/02/14
     * @author 이해빈
     */
    @ResponseBody
    @PostMapping("product/deleteCart")
    public Map<String, Integer> deleteCart(@RequestBody HashMap<String, Object> checkboxArr) {

        int result = 0;

        result = service.deleteCarts(checkboxArr);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }    
    
    /**
     * 장바구니 선택 주문
     * @since 23/02/14
     * @author 이해빈
     */
    @ResponseBody
    @PostMapping("product/goOrder")
    public Map<String, Integer> goOrder(@RequestBody HashMap<String, Object> checkboxArr, HttpSession session) {

        int result = 0;

        List<CartDTO> cartsDTOs = service.getCarts(checkboxArr);

        if(!cartsDTOs.isEmpty()){
            result = 1;
        }

        // 세션에 저장된 carts, prodNo가 있으면 초기화 후 세션에 저장
        Object carts = session.getAttribute("carts");
        Object prodNo = session.getAttribute("prodNo");

        if (carts != null) {
            session.removeAttribute("carts");
        }

        if (prodNo != null) {
            session.removeAttribute("prodNo");
        }

        session.setAttribute("carts",  cartsDTOs);

        Map<String, Integer> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }

    /**
     * 상품 검색
     * @since 23/02/21
     * @author 이해빈
     */

    @GetMapping("product/search")
    public String search(Model model, String pg, String sort, SearchDTO dto){

        if(sort == null){
            sort = "sold";
        }

        String keyword = dto.getKeyword();
        String keywords = dto.getKeywords();

        int total = 0;
        List<productVO> products = null;

        log.info("dto :" + dto);

        // 게시글 출력 갯수
        int count = 10;

        // 현재 페이지 번호
        int currentPage = service.getCurrentPage(pg);

        // 페이지 시작값
        int start = service.getLimitStart(currentPage, count);

        if(keywords == null || keywords.equals("")){ // 1차 검색일때
            // 전체 게시물 갯수
            total = service.getCountTotalForSearch(keyword);
        }else{ //2차 검색일 때
            total = service.getCountTotalForSearch2(dto);
        }

        log.info("total값 :" + total);


        // 페이지 마지막 번호
        int lastPageNum = service.getLastPageNum(total, count);

        // 페이지 그룹 start, end 번호
        int[] pageGroup = service.getPageGroupNum(currentPage, lastPageNum);

        // 현재 페이지 상품 가져오기
        if(keywords == null || keywords.equals("")){ // 1차 검색일때
            // 전체 게시물 갯수
            products = service.selectProductsForSearch(sort, start, keyword);
        }else{ //2차 검색일 때
            products = service.selectProductsForSearch2(sort, start, dto);
        }


        model.addAttribute("products", products);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageGroup", pageGroup);

        model.addAttribute("keyword", keyword);
        model.addAttribute("keywords", dto.getKeywords());
        model.addAttribute("chk1", dto.isChk1());
        model.addAttribute("chk2", dto.isChk2());
        model.addAttribute("chk3", dto.isChk3());
        model.addAttribute("min", dto.getMin());
        model.addAttribute("max", dto.getMax());
        model.addAttribute("sort", sort);
        model.addAttribute("total", total);

        return "product/search";
    }

}
