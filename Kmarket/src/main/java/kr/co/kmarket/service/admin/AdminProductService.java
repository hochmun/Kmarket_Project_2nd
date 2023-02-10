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
     * @since 2023/02/09 // 심규영 // 관리자 상품 리스트 불러오는 메소드 작성
     * @param uid
     * @param start
     * @return
     */
    public List<productVO> selectProducts(String uid, int start){
        if(uid == "") uid = "%"+uid+"%";
        return dao.selectProducts(uid, start);
    }

    /**
     * 관리자 상품 리스트 갯수 구하기
     * @since 2023/02/09 // 심규영 // 관리자 상품 리스트 갯수 구하기 작성
     * @param uid
     * @return 상품 갯수
     */
    public int selectCountProduct(String uid) {
        if(uid == "") uid = "%"+uid+"%";
        return dao.selectCountProduct(uid);
    }
    // upload

    // delete

    // service

}
