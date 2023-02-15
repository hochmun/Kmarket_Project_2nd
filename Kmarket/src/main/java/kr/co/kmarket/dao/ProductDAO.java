package kr.co.kmarket.dao;

import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.vo.productVO;
import kr.co.kmarket.vo.product_cate2VO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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

    /**
     * 상품 가져오기
     * @since 23/02/10
     * @author 이해빈
     */
    public productVO selectProduct(int prodNo);

    /**
     * 상품 조회수 +1
     * @since 23/02/10
     * @author 이해빈
     */
    public void updateProductHit(int prodNo);
    
    /**
     * 상품 장바구니 추가
     * @since 23/02/12
     * @author 이해빈
     */
    public int addCart(CartDTO cart);

    /**
     * 상품 장바구니 목록
     * @since 23/02/13
     * @author 이해빈
     */
    public List<CartDTO> selectCarts(String uid);    
    
    /**
     * 상품 주문할 장바구니 목록 가져오기
     * @since 23/02/13
     * @author 이해빈
     */
    public List<CartDTO> getCarts(HashMap<String, Object> checkboxArr);

    /**
     * 상품 장바구니 삭제
     * @since 23/02/14
     * @author 이해빈
     */
    public int deleteCart(HashMap<String, Object> checkboxArr);
    
}
