package kr.co.kmarket.dao.admin;

import kr.co.kmarket.vo.BannerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AdminConfigDAO {
    
    /**
     * 2023/02/23 
     * 배너 등록
     * @author 이해빈
     * */
    
    public int insertBanner(BannerVO banner);


    /**
     * 2023/02/23 
     * 배너 가져오기
     * @author 이해빈
     * */
    public List<BannerVO> selectBanners();

    /**
     * 2023/02/23
     * 배너 선택삭제하기
     * @author 이해빈
     * */
    public int deleteBanner(HashMap<String, Object> checkboxArr);    
    
    /**
     * 2023/02/23
     * 배너 활성화/비활성화 상태변경
     * @author 이해빈
     * */
    public int changeBannerStatus(@Param("no") int no, @Param("status")int status);


}
