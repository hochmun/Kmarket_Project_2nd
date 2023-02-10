package kr.co.kmarket.service.admin;

import kr.co.kmarket.dao.admin.AdminProductDAO;
import kr.co.kmarket.vo.productVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 관리자 상품 서비스 <br>
 * @since 2023/02/09 // 심규영 // 관리자 상품 서비스 생성
 */
@Service
public class AdminProductService {

    /**
     * @since 2023/02/09 // 심규영 // 관리자 상품 DAO 연결
     */
    @Autowired
    private AdminProductDAO dao;

    // create

    // read

    /**
     * 관리자 상품 리스트 불러오기<br>
     * 유저 아이디에 마스터피스 추가
     * 검색기능 추가
     * @since 2023/02/09 // 심규영 // 관리자 상품 리스트 불러오는 메소드 작성<br>
     *        2023/02/10 // 심규영 // 관리자 상품 리스트 검색 기능 추가
     * @param uid
     * @param start
     * @return
     */
    public List<productVO> selectProducts(String uid, int start, String s, String st){
        if(uid.equals("")) uid = "%"+uid+"%";
        if(st.equals("")) st = "prodNo";
        s = "%"+s+"%";
        return dao.selectProducts(uid, start, s, st);
    }

    /**
     * 관리자 상품 리스트 갯수 구하기
     * @since 2023/02/09 // 심규영 // 관리자 상품 리스트 갯수 구하기 작성<br>
     * 2023/02/10 // 심규영 // 검색 기능 추가
     * @param uid
     * @return 상품 갯수
     */
    public int selectCountProduct(String uid, String s, String st) {
        if(uid.equals("")) uid = "%"+uid+"%";
        if(st.equals("")) st = "prodNo";
        s = "%"+s+"%";
        return dao.selectCountProduct(uid, s, st);
    }
    // upload

    // delete

    // service

}
