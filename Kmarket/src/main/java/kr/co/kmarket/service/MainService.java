package kr.co.kmarket.service;

import kr.co.kmarket.dao.MainDAO;
import kr.co.kmarket.vo.productVO;
import kr.co.kmarket.vo.product_cate1VO;
import kr.co.kmarket.vo.product_cate2VO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2023/02/10 // 심규영 // 메인 서비스 생성
 */
@Slf4j
@Service
public class MainService {

    @Autowired
    private MainDAO dao;

    // create

    // read
    /**
     * 2023/02/10 // 심규영 // 카테고리1 불러오기
     * @return
     */
    public List<product_cate1VO> selectCate1s(){
        return dao.selectCate1s();
    }

    /**
     * 2023/02/10 // 심규영 // 카테고리2 불러오기
     * @return
     */
    public List<product_cate2VO> selectCate2s(){
        return dao.selectCate2s();
    }

    /**
     * 2023/02/10 // 심규영 // 카테고리1의 카테고리2값 가져오기
     * @param cate1
     * @return
     */
    public List<product_cate2VO> selectCate2WithCate1(String cate1){
        return dao.selectCate2WithCate1(Integer.parseInt(cate1));
    }

    /**
     * 베스트 상품 불러오기 및 할인된 가격 계산
     * 2023/02/10 // 심규영 // 베스트 상품 불러오기
     * @return
     */
    public List<productVO> selectProductBest(){
        List<productVO> bests = dao.selectProductBest();
        for(productVO best : bests) {
            best.setDisPrice((int) (best.getPrice() * (1 - ( best.getDiscount() / 100.0 ))));
        }
        return bests;
    }

    /**
     * 2023/02/10 // 심규영 // 메인 상품 8개 불러오기
     * @param mode
     * @return
     */
    public List<productVO> selectProductMode(String mode){
        List<productVO> products = dao.selectProductMode(mode);
        for(productVO product : products) {
            product.setDisPrice((int) (product.getPrice() * (1 - ( product.getDiscount() / 100.0 ))));
        }
        return products;
    }
    // upload

    // delete

    // service

}
