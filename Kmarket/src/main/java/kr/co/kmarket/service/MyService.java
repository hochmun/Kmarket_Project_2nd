package kr.co.kmarket.service;

import kr.co.kmarket.dao.MyDAO;
import kr.co.kmarket.vo.Cs_QnaVO;
import kr.co.kmarket.vo.member_pointVO;
import kr.co.kmarket.vo.product_orderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    /**
     * 2023/02/21 // 심규영 // 마이서비스 dao 연결
     */
    @Autowired
    private MyDAO dao;

    // create

    // read

    /**
     * 2023/02/21 // 심규영 // 마이페이지 홈 최근 주문 내역 5개 불러오는 기능
     * @param uid
     */
    public List<product_orderVO> selectMyOrder(String uid) {
        return dao.selectMyOrder(uid);
    }

    /**
     * 2023/02/21 // 심규영 // 마이페이지 홈 최근 포인트 적립 내역 5개 불러오는 기능
     */
    public List<member_pointVO> selectMyPointList5(String uid) {
        return dao.selectMyPointList5(uid);
    }

    /**
     * 2023/02/21 // 심규영 // 마이페이지 홈 최근 문의 내역 5개 불러오는 기능
     * @param uid
     * @return
     */
    public List<Cs_QnaVO> selectMyQnaList5(String uid) {
        return dao.selectMyQnaList5(uid);
    }

    // upload

    // delete

    // service
}
