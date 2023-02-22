package kr.co.kmarket.controller;

import kr.co.kmarket.service.TermsPolicyService;
import kr.co.kmarket.vo.TermsPolicyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class TermsPolicyController {

    @Autowired
    private TermsPolicyService service;

    /**
     * 2023/02/21 // 이해빈 // termsPolicy 기능구현
     * */
    @GetMapping("termsPolicy/policy")
    public String termsPolicy(Model model, int type){
        Map<Integer, List<TermsPolicyVO>> policy = service.selectPolicy(type);
        String typeName = service.getTypeName(type);

        model.addAttribute("type", type);
        model.addAttribute("policys", policy);
        model.addAttribute("typeName", typeName);

        return "termsPolicy/policy";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("/termsPolicy/buyer")
    public String buyer(){
        return "/termsPolicy/buyer";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("/termsPolicy/seller")
    public String seller(){
        return "/termsPolicy/seller";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("/termsPolicy/finance")
    public String finance(){
        return "/termsPolicy/finance";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("/termsPolicy/location")
    public String location(){
        return "/termsPolicy/location";
    }

    /**
     * 2023/02/20 // 심규영 // 기본 맵핑 생성
     * @return
     */
    @GetMapping("/termsPolicy/privacy")
    public String privacy(){
        return "/termsPolicy/privacy";
    }
}
