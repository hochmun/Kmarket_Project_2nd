package kr.co.kmarket.dao;

import kr.co.kmarket.vo.productVO;
import kr.co.kmarket.vo.product_cate2VO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductDAO {

    /**
     * 상품 list dao
     * @since 23/02/09
     * @author 이해빈
     */
    public List<productVO> selectProducts(@Param("cate1") int cate1, @Param("cate2") int cate2, @Param("sort") String sort, @Param("start") int start);

    /**
     * 상품 카테고리명 가져오기 dao
     * @since 23/02/09
     * @author 이해빈
     */
    public product_cate2VO getCateName(@Param("cate1") int cate1, @Param("cate2") int cate2);

    /**
     * 상품 전체 상품 갯수
     * @since 23/02/09
     * @author 이해빈
     */
    public int getCountTotal(@Param("cate1") int cate1, @Param("cate2") int cate2);
}
