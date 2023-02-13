package kr.co.kmarket.service;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.dto.CartDTO;
import kr.co.kmarket.vo.productVO;
import kr.co.kmarket.vo.product_cate2VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO dao;

    /**
     * 상품 list service
     * @since 23/02/09
     * @author 이해빈
     */
    public List<productVO> selectProducts(int cate1, int cate2, String sort, int start){
        return dao.selectProducts(cate1, cate2, sort, start);
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
    public int getCountTotal(int cate1, int cate2) {
        return dao.getCountTotal(cate1, cate2);
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
    public int getLastPageNum(int total) {

        int lastPage = 0;

        if(total % 10 == 0) {
            lastPage = (total / 10);
        }else {
            lastPage = (total / 10) + 1;
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
    public int getLimitStart(int currentPage) {
        return (currentPage - 1) * 10;
    }

}
