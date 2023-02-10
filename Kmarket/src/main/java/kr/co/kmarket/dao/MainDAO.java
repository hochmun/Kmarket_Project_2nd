package kr.co.kmarket.dao;

import kr.co.kmarket.vo.productVO;
import kr.co.kmarket.vo.product_cate1VO;
import kr.co.kmarket.vo.product_cate2VO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 2023/02/10 // 심규영 // 메인 DAO 생성
 */
@Mapper
@Repository
public interface MainDAO {
    // create
    // read

    /**
     * 2023/02/10 // 심규영 // 카테고리1 불러오기
     * @return
     */
    public List<product_cate1VO> selectCate1s();

    /**
     * 2023/02/10 // 심규영 // 카테고리2 불러오기
     * @return
     */
    public List<product_cate2VO> selectCate2s();

    /**
     * 2023/02/10 // 심규영 // 카테고리1의 카테고리2값 가져오기
     * @param cate1
     * @return
     */
    public List<product_cate2VO> selectCate2WithCate1(int cate1);

    /**
     * 2023/02/10 // 심규영 // 베스트 상품 불러오기
     * @return
     */
    public List<productVO> selectProductBest();

    /**
     * 2023/02/10 // 심규영 // 메인 상품 8개 불러오기
     * @param mode
     * @return
     */
    public List<productVO> selectProductMode(String mode);
    // upload
    // delete
}
