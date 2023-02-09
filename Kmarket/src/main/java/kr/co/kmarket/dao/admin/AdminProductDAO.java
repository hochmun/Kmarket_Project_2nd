package kr.co.kmarket.dao.admin;

import kr.co.kmarket.vo.productVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 관리자 상품 DAO 클래스
 * @since  2023/02/09 // 심규영 // 관리자 상품 DAO 생성
 */
@Mapper
@Repository
public interface AdminProductDAO {
    // create

    // read
    /**
     * 관리자 상품 리스트 불러오기
     * @since 2023/02/09 // 심규영 // 관리자 상품 리스트 불러오기 작성
     * @return {@code List<productVO>} // 상품 리스트 리턴
     */
    public List<productVO> selectProducts(@Param("uid") String uid, @Param("start") int start);

    /**
     * 관리자 상품 리스트 갯수 구하기
     * @since 2023/02/09 // 심규영 // 관리자 상품 리스트 갯수 구하기 작성
     */
    public int selectCountProduct(String uid);

    // upload

    // delete

}
