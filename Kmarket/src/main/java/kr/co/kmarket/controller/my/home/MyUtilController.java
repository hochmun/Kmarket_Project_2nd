package kr.co.kmarket.controller.my.home;

import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.MyService;
import kr.co.kmarket.vo.Product_qnaVO;
import kr.co.kmarket.vo.memberVO;
import kr.co.kmarket.vo.product_orderVO;
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
}
