package kr.co.kmarket.controller.my.home;

import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.MyService;
import kr.co.kmarket.vo.Product_qnaVO;
import kr.co.kmarket.vo.memberVO;
import kr.co.kmarket.vo.product_orderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *  2023/02/22 // 심규영 // 마이페이지 home 팝업 맵핑 모음
 */
@Controller
@Slf4j
public class MyUtilController {

    /**
     * 2023/02/22 // 심규영 // 서비스 연결
     */
    @Autowired
    private MyService service;

    /**
     * 2023/02/22 // 심규영 // 회사 정보 불러오기
     * 검색 값을 company로 하지 않는 이유는 company는 중복 값이 있을 수 있기 때문에
     *      들어오는 값
     *          uid
     */
    @ResponseBody
    @GetMapping("my/home/getCompanyStatus")
    public Map<String, Object> getCompanyStatus(@RequestParam("uid") String uid) {
        // 반환 하는 결과 값
        int result = 0;
        
        // uid로 유저 정보 불러오기
        memberVO vo = service.selectGetCompanyStatus(uid);
        if(vo != null) result = 1;

        // 반환 하는 맵 생성
        Map<String, Object> resultData =  new HashMap<>();
        resultData.put("result", result);
        resultData.put("vo", vo);

        // 리턴
        return resultData;
    }

    /**
     * 2023/02/22 // 심규영 // 주문 상태 불러오기
     * @param ordNo
     * @return
     */
    @ResponseBody
    @GetMapping("my/home/getOrderStatus")
    public Map<String, Object> getOrderStatus(@RequestParam("ordNo") int ordNo) {
        // 결과 값 선언
        int result = 0;
        
        // 주문 정보 가져오기
        List<product_orderVO> vos = service.selectGetOrderStatus(ordNo);
        if(vos != null) result = 1;

        // 리턴 할 맵 선언
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);
        resultMap.put("vos", vos);
        
        // 리턴
        return resultMap;
    }

    /**
     * 2023/02/23 // 심규영 // 상품 문의 올리기
     * @param vo
     * @param req
     * @param myUserDetails
     * @return
     */
    @ResponseBody
    @PostMapping("my/home/uploadProductQna")
    public Map<String, Object> uploadProductQna(@RequestBody Product_qnaVO vo,
                                                HttpServletRequest req,
                                                @AuthenticationPrincipal MyUserDetails myUserDetails) {
        // 결과 값 선언
        int result = 0;
        
        // vo에 값 입력
        vo.setPqnaRegip(req.getRemoteAddr());
        vo.setPqnaEnUid(myUserDetails.getUser().getUid());
        
        // 문의글 업로드
        result = service.insertProductQna(vo);

        // 리턴 할 맵 선언
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);

        // 리턴
        return resultMap;
    }

    /**
     * 2023/02/23 // 심규영 // 상품 수령 확인 기능
     * <p>
     *      들어오는 값<br>
     *      ordNo => 주문 번호
     *     </p>
     */
    @ResponseBody
    @PostMapping("my/home/receiptConfirm")
    public Map<String, Object> receiptConfirm(@RequestBody Map<String, String> data,
                               @AuthenticationPrincipal MyUserDetails myUserDetails) {
        // 리턴 값 선언
        int result = 0;

        // 수취 확인 및 유저 포인트 적립
        result += service.updateProductOrderAndPoint(data.get("ordNo"), myUserDetails.getUser().getUid());

        // 포인트 적립 내용 기록
        result += service.insertSaveMemberPoint(data.get("ordNo"), myUserDetails.getUser().getUid());

        // 리턴 할 맵 선언
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);

        // 리턴
        return resultMap;
    }

    /**
     * 2023/02/23 // 심규영 // 마이페이지 홈 리뷰 작성
     * <p>
     *     들어오는 값 (prodNo:상품번호, ordNo:주문번호, starRating:평점, review: 리뷰내용)
     * </p>
     * @param data
     * @param myUserDetails
     */
    @ResponseBody
    @PostMapping("my/home/insertReview")
    public Map<String, Object> insertReview(@RequestBody Map<String, String> data,
                             @AuthenticationPrincipal MyUserDetails myUserDetails,
                                            HttpServletRequest req) {
        // 리턴 값 선언
        int result = -1;
        
        // data에 값 넣기
        data.put("uid", myUserDetails.getUser().getUid());
        data.put("regip", req.getRemoteAddr());

        // 리뷰 테이블에 리뷰 추가 하기(insert)
        try {
            result = service.insertProductReview(data);
        } catch (Exception e) {
            result = 0;
        }

        // 상품 테이블에 리뷰갯수 증가 및 리뷰 점수 증가(update)
        // 리뷰 테이블에 리뷰 추가에 성공한 경우
        if(result > 0) service.updateProductScoreAndReivew(data);

        // 리턴 하는 맵 선언
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);

        // 리턴
        return resultMap;

    }

    @ResponseBody
    @PostMapping("/my/myShoppingStatus")
    public void myShoppingStatus(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        // 아이디 받아오기
        String uid = myUserDetails.getUser().getUid();

        //주문. 배송 갯수
        
        // 할인 쿠폰 갯수'
        
        // 포인트 량
        
        // 문의 내역 갯수
    }

}
