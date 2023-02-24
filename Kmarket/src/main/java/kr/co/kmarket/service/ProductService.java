package kr.co.kmarket.service;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.dto.SearchDTO;
import kr.co.kmarket.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductDAO dao;

    /**
     * 상품 list service
     * @since 23/02/09
     * @author 이해빈
     */
    public List<productVO> selectProducts(int cate1, int cate2, String sort, int start, String search){
        return dao.selectProducts(cate1, cate2, sort, start, search);
    }

    /**
     * 상품 검색 list
     * @since 23/02/21
     * @author 이해빈
     */
    public List<productVO> selectProductsForSearch(String sort, int start, String keyword){
        return dao.selectProductsForSearch(sort, start, keyword);
    }

    /**
     * 상품 2차 검색 list
     * @since 23/02/21
     * @author 이해빈
     */
    public List<productVO> selectProductsForSearch2(String sort, int start, SearchDTO dto){
        return dao.selectProductsForSearch2(sort, start, dto);
    }

    /**
     * 상품 검색 list
     * @since 23/02/21
     * @author 이해빈
     */
    public List<productVO> selectProductsForSearch2(HashMap<String, Object> map){
        return dao.selectProductsForSearch2(map);
    }

    /**
     * 상품 카테고리명 가져오기 service
     * @since 23/02/09
     * @author 이해빈
     */
    public product_cate2VO getCateName(int cate1, int cate2){
        return dao.getCateName(cate1, cate2);
    }

    /**
     * 상품 전체 상품 갯수
     * @since 23/02/09
     * @author 이해빈
     */
    public int getCountTotal(int cate1, int cate2, String search) {
        return dao.getCountTotal(cate1, cate2, search);
    } 
    /**
     * 상품 검색결과 갯수
     * @since 23/02/21
     * @author 이해빈
     */
    public int getCountTotalForSearch(String keyword) {
        return dao.getCountTotalForSearch(keyword);
    }

    /**
     * 상품 2차 검색결과 갯수
     * @since 23/02/22
     * @author 이해빈
     */
    public int getCountTotalForSearch2(SearchDTO dto) {
        return dao.getCountTotalForSearch2(dto);
    }
    


    /**
     * 상품 가져오기
     * @since 23/02/10
     * @author 이해빈
     */
    @Transactional
    public productVO selectProduct(int prodNo){

        productVO product = dao.selectProduct(prodNo);

        // 조회수 + 1
        dao.updateProductHit(prodNo);

        return product;
    }
    /**
     * 상품 장바구니 추가
     * @since 23/02/12
     * @author 이해빈
     */
    public int addCart(CartDTO cart){
        return dao.addCart(cart);
    }

    /**
     * 상품 장바구니 목록
     * @since 23/02/13
     * @author 이해빈
     */
    public List<CartDTO> selectCarts(String uid){
        return dao.selectCarts(uid);
    }

    /**
     * 상품 주문할 cart목록 가져오기
     * @since 23/02/14
     * @author 이해빈
     */
    public List<CartDTO> getCarts(HashMap<String, Object> checkboxArr){
        return dao.getCarts(checkboxArr);
    }
    
    /**
     * 상품 장바구니 삭제
     * @since 23/02/14
     * @author 이해빈
     */
    public int deleteCarts(HashMap<String, Object> checkboxArr){
        return dao.deleteCarts(checkboxArr);
    }


    /**
     * 주문내용 DB 업데이트
     * @since 23/02/15
     * @author 이해빈
     */
    @Transactional
    public int updateOrder(List<String> cartNos, Map<String, Object> orderinfo){

        int result = 0;
        int size = cartNos.size();
        int usedPoint = Integer.parseInt(String.valueOf(orderinfo.get("usedPoint")));

        // 회원 정보 포인트 업데이트
        result += dao.updatePoint(orderinfo);

        // 주문 테이블 업데이트
        result += dao.updateOrder(orderinfo);

        // 주문 테이블 업데이트 후 ordNo 값을 리턴받음
        BigInteger ordNoBigInt = (BigInteger) orderinfo.get("ordNo");
        int ordNo = ordNoBigInt.intValueExact();

        orderinfo.put("ordNo", ordNo);

        // 포인트 테이블 업데이트
        // result += dao.insertSavePoint(orderinfo);

        // 사용한 포인트가 있을 경우
        if(usedPoint > 0){
            dao.insertUsedPoint(orderinfo);
        }

        // view -> 주문의경우
        if(size == 1 && Integer.parseInt(cartNos.get(0))==0){

            // 주문번호 가져오기
            List<String> prodNos = (List<String>) orderinfo.get("prodNoArr");
            int prodNo = Integer.parseInt(prodNos.get(0));

            orderinfo.put("ordNo", ordNo);
            orderinfo.put("prodNo", prodNo);

            // 주문 상품 테이블 업데이트
            result += dao.insertOrderItem2(orderinfo);


        }else{ // 장바구니 -> 주문의 경우
            for(int i = 0; i < cartNos.size(); i++) {
                int cartNo = Integer.parseInt(cartNos.get(i));

                // 주문 상품 테이블 업데이트
                result += dao.insertOrderItem(cartNo, ordNo);

                // 주문한 상품 장바구니에서 삭제
                result += dao.deleteCart(cartNo);

            }
        }


        // 모든 테이블 업데이트가 정상적으로 실행되었을 경우 주문번호를 리턴, 그렇지 않으면 0 리텅
        if(size > 0 && result == size * 2 + 2){ // 장바구니 -> 주문
            return ordNo;
        }else if(size == 1 && result == 3){ // view -> 주문
            return ordNo;
        }else{
            return 0;
        }
    };

    /**
     * 주문정보 가져오기
     * @since 23/02/15
     * @author 이해빈
     */
    public product_orderVO selectOrder(int ordNo){
        return dao.selectOrder(ordNo);
    }

    /**
     * 주문한 아이템 가져오기
     * @since 23/02/15
     * @author 이해빈
     */
    public List<product_order_itemVO> selectOrderItems(@Param("ordNo") int ordNo){
        return dao.selectOrderItems(ordNo);
    }

    /**
     * 상품 리뷰 가져오기
     * @since 23/02/16
     * @author 이해빈
     * */
    public List<product_reviewVO> selectReviews(int prodNo, int start){
        return dao.selectReviews(prodNo, start);
    }

    /**
     * 현재 페이지
     * @since 23/02/09
     * @author 이해빈
     */
    public int getCurrentPage(String pg) {
        int currentPage = 1;

        if(pg != null) {
            currentPage = Integer.parseInt(pg);
        }
        return currentPage;
    }

    /**
     * 마지막 페이지 번호
     * @since 23/02/09
     * @author 이해빈
     */
    public int getLastPageNum(int total, int count) {

        int lastPage = 0;

        if(total % count == 0) {
            lastPage = (total / count);
        }else {
            lastPage = (total / count) + 1;
        }

        if(lastPage == 0){
            lastPage = 1;
        }

        return lastPage;
    }

    /**
     * 페이지 그룹
     * @since 23/02/09
     * @author 이해빈
     */
    public int[] getPageGroupNum(int currentPage, int lastPage) {

        int groupCurrent = (int) Math.ceil(currentPage / 10.0);
        int groupStart = (groupCurrent - 1) * 10 + 1;
        int groupEnd = groupCurrent * 10;

        if(groupEnd > lastPage) {
            groupEnd = lastPage;
        }

        int[] group = {groupStart, groupEnd};

        return group;
    }

    /**
     * 페이지 시작값
     * @since 23/02/09
     * @author 이해빈
     */
    // 페이지 시작값
    public int getLimitStart(int currentPage, int count) {
        return (currentPage - 1) * count ;
    }


    /**
     * 상품 리뷰 total 값 가져오기
     * @since 23/02/16
     * @author 이해빈
     * */
    public int getCountTotalForReview(@Param("prodNo") int prodNo){
        return dao.getCountTotalForReview(prodNo);
    }

}
