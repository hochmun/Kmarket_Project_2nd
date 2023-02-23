package kr.co.kmarket.service.admin;

import kr.co.kmarket.dao.admin.AdminConfigDAO;
import kr.co.kmarket.vo.BannerVO;
import kr.co.kmarket.vo.TermsPolicyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminConfigService {

    @Autowired
    private AdminConfigDAO dao;

    /**
     * 2023/02/23
     * 배너 등록
     * @author 이해빈
     * */

    public int insertBanner(BannerVO banner){
        return dao.insertBanner(banner);
    }

    /**
     * 2023/02/23
     * 배너 가져오기
     * @author 이해빈
     * */
    public Map<String, List<BannerVO>> selectBanners(){

        List<BannerVO> banners = dao.selectBanners();
        return banners.stream().collect(Collectors.groupingBy(BannerVO::getBannerPosition));
    }

}
