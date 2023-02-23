package kr.co.kmarket.controller.admin;

import kr.co.kmarket.security.MyUserDetails;
import kr.co.kmarket.service.admin.AdminConfigService;
import kr.co.kmarket.vo.BannerVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class AdminConfigController {

    @Autowired
    private AdminConfigService service;


    /**
     * 2023/02/23 // 이해빈 // banner 기본 맵핑
     * */
    @GetMapping("admin/config/banner")
    public String banner(@AuthenticationPrincipal MyUserDetails myUser, Model model){

        Map<String, List<BannerVO>> map = service.selectBanners();


        model.addAttribute("name", myUser.getUser().getName());
        model.addAttribute("map", map);

        return "admin/config/banner";
    }

    /**
     * 2023/02/23 // 이해빈 // banner 등록
     * * */
    @PostMapping("admin/config/banner")
    public String banner(BannerVO vo){

        log.info("name :" + vo.getBannerName());
        log.info("size :" + vo.getBannerSize());
        log.info("color :" + vo.getBannerColor());
        log.info("link :" + vo.getBannerLink());
        log.info("position :" + vo.getBannerPosition());
        log.info("sdate :" + vo.getBannerSdate());
        log.info("edate :" + vo.getBannerEdate());
        log.info("stime :" + vo.getBannerStime());
        log.info("etime :" + vo.getBannerEtime());

        service.insertBanner(vo);

        int no = vo.getBannerNo();
        log.info("반환된 no: " +no);

        return "redirect:/admin/config/banner";

    }

}
