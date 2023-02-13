package kr.co.kmarket.controller.admin;

import kr.co.kmarket.entity.UserEntity;
import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.MainService;
import kr.co.kmarket.util.PagingUtil;
import kr.co.kmarket.dto.PagingDTO;
import kr.co.kmarket.service.admin.AdminProductService;
import kr.co.kmarket.vo.productVO;
import kr.co.kmarket.vo.product_cate1VO;
import kr.co.kmarket.vo.product_cate2VO;
import lombok.extern.slf4j.Slf4j;
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
                       @RequestParam(value = "st", required = false,defaultValue = "") String st,
                       @AuthenticationPrincipal MyUserDetails myUserDetails) {
        UserEntity user = myUserDetails.getUser();

        // 접속한 유저가 관리자일시 전체 상품 보기, 아닐경우 자신의 상품만 보기
        String uid;
        if(user.getType() == 5) uid = "%%";
        else uid = user.getUid();

        // 페이징 처리
        PagingDTO paging = new PagingUtil().getPagingDTO(pg, service.selectCountProduct(uid, s, st));

        // 게시물 불러오기
        List<productVO> products = service.selectProducts(uid, paging.getStart(), s, st);

        // 모델 전송
        model.addAttribute("s", s);
        model.addAttribute("st", st);
        model.addAttribute("paging", paging);
        model.addAttribute("products", products);
        model.addAttribute("name", user.getName());

        // 리턴
        return "admin/product/list";
    }

    /**
     * 관리자 상품 등록 페이지 Get맵핑
     * @since 2023/02/09 // 심규영 // 최초 작성
     * @return
     */
    @GetMapping("admin/product/register")
    public String register(Model model,
                           @AuthenticationPrincipal MyUserDetails myUserDetails) {
        List<product_cate1VO> cate1s = mainService.selectCate1s();

        model.addAttribute("cate1s", cate1s);
        model.addAttribute("name", myUserDetails.getUser().getName());

        return "admin/product/register";
    }

    /**
     * <br>2023/02/13 // 심규영 // 상품 업로드 작성
     * @param vo
     */
    @PostMapping("admin/product/register")
    public String register(productVO vo,
                         HttpServletRequest req,
                         @AuthenticationPrincipal MyUserDetails myUser) {
        UserEntity user = myUser.getUser();

        vo.setUid(user.getUid());
        vo.setIp(req.getRemoteAddr());
        vo.setSeller(user.getCompany()); // 셀러를 회사 이름에서 가져옴

        service.insertProduct(vo);

        return "redirect:/admin/product/register";
    }

    /**
     * 카테고리 1값에 따른 카테고리2값 리턴
     * <br>2023/02/10 // 심규영 // 최초작성
     * @param cate1
     * @return
     */
    @ResponseBody
    @GetMapping("admin/product/getCate2")
    public Map<String, Object> getCate2(@RequestParam("cate1") String cate1) {
        List<product_cate2VO> cate2s = mainService.selectCate2WithCate1(cate1);

        Map<String, Object> result = new HashMap<>();
        result.put("cate2s", cate2s);

        return result;
    }

    /**
     * 관리자 상품 삭제 기능
     * <br> 다중 처리 기능 추가
     * @param prodNo
     * @return
     * @since 2023/02/13 // 심규영 // 최초 작성
     */
    @ResponseBody
    @GetMapping("/admin/product/deleteProduct")
    public Map<String, Object> deleteProduct(@RequestParam("prodNo") String prodNo,
                                             @AuthenticationPrincipal MyUserDetails myUserDetails) {
        UserEntity user = myUserDetails.getUser();
        String uid = user.getUid();
        int type = user.getType();

        String[] arrays = prodNo.split(",");

        int result = service.updateProductDeleteStatus(arrays, uid, type);

        Map<String, Object> data = new HashMap<>();
        data.put("result", result);

        return data;
    }

    /**
     * 관리자 상품 리스트 - 상품 수정 기능
     * @since 2023/02/13 // 심규영 // 상품 수정 기능
     * @param map
     * @param myUserDetails
     * @return
     */
    @ResponseBody
    @PutMapping("/admin/product/modifyProduct")
    public Map<String, Object> modifyProduct(@RequestBody HashMap<String, Object> map,
                                             @AuthenticationPrincipal MyUserDetails myUserDetails){
        int result = service.updateProduct(map, myUserDetails.getUser().getUid());

        Map<String, Object> data = new HashMap<>();
        data.put("result", result);

        return data;
    }

}
