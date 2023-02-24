package kr.co.kmarket.dao;

import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.dto.SearchDTO;
import kr.co.kmarket.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProductDAO {

    /**
     * 상품 list dao
     * @since 23/02/09
     * @author 이해빈
     */
    public List<productVO> selectProducts(@Param("cate1") int cate1, @Param("cate2") int cate2,
                                          @Param("sort") String sort, @Param("start") int start, @Param("search") String search);
    /**
     * 상품 검색 list
     * @since 23/02/21
     * @author 이해빈
     */
    public List<productVO> selectProductsForSearch(@Param("sort")String sort, @Param("start")int start, @Param("keyword") String keyword);

    /**
     * 상품 2차 검색 list
     * @since 23/02/21
     * @author 이해빈
     */
    public List<productVO> selectProductsForSearch2(@Param("sort") String sort, @Param("start") int start, @Param("dto") SearchDTO dto);

    /**
     * 상품 2차 검색 list
     * @since 23/02/21
     * @author 이해빈
     */
    public List<productVO> selectProductsForSearch2(HashMap<String, Object> map);


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
    public int getCountTotal(@Param("cate1") int cate1, @Param("cate2") int cate2, @Param("search") String search);

    /**
     * 상품 검색결과 갯수
     * @since 23/02/21
     * @author 이해빈
     */
    public int getCountTotalForSearch(@Param("keyword") String keyword);

    /**
     * 상품 검색결과 갯수
     * @since 23/02/22
     * @author 이해빈
     */
    public int getCountTotalForSearch2(SearchDTO dto);

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
    public int deleteCarts(HashMap<String, Object> checkboxArr);

    /**
     * 상품 주문관련 회원 포인트내역 업데이트
     * @since 23/02/15
     * @author 이해빈
     */
    public int updatePoint(Map<String, Object> orderinfo);

    /**
     * 상품 주문관련 상품 포인트 테이블 업데이트
     * @since 23/02/16
     * @author 이해빈
     */
    public int insertUsedPoint(Map<String, Object> orderinfo);
    public int insertSavePoint(Map<String, Object> orderinfo);

    /**
     * 상품 주문(km_product_order) 테이블 업데이트
     * @since 23/02/15
     * @author 이해빈
     */
    public int updateOrder(Map<String, Object> orderinfo);

    /**
     * 주문한 상품(km_product_order_item) 테이블 업데이트
     * 장바구니 -> order
     * @since 23/02/15
     * @author 이해빈
     */
    public int insertOrderItem(@Param("cartNo") int cartNo, @Param("ordNo") int ordNo);

    /**
     * 주문한 상품(km_product_order_item) 테이블 업데이트
     * view -> 주문
     * @since 23/02/16
     * @author 이해빈
     */
    public int insertOrderItem2(Map<String ,Object> orderinfo);

    /**
     * 주문한 상품을 장바구니에서 삭제
     * @since 23/02/15
     * @author 이해빈
     */
    public int deleteCart(@Param("cartNo") int cartNo);

    /**
     * 주문정보 가져오기
     * @since 23/02/15
     * @author 이해빈
     */
    public product_orderVO selectOrder(@Param("ordNo") int ordNo);    
    
    /**
     * 주문한 아이템 가져오기
     * @since 23/02/15
     * @author 이해빈
     */
    public List<product_order_itemVO> selectOrderItems(@Param("ordNo") int ordNo);

    
    /**
     * 상품 리뷰 가져오기
     * @since 23/02/16
     * @author 이해빈
     * */
    public List<product_reviewVO> selectReviews(@Param("prodNo") int prodNo, @Param("start") int start);

    /**
     * 상품 리뷰 total 값 가져오기
     * @since 23/02/16
     * @author 이해빈
     * */
    public int getCountTotalForReview(@Param("prodNo") int prodNo);

}

