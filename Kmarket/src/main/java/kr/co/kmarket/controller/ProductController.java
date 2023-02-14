package kr.co.kmarket.controller;

import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.entity.UserEntity;
import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.memberVO;
import kr.co.kmarket.vo.productVO;
import kr.co.kmarket.vo.product_cate2VO;
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

    @GetMapping("product/complete")
    public String complete(){

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

        result = service.deleteCart(checkboxArr);

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
}
