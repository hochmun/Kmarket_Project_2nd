package kr.co.kmarket.service;

import kr.co.kmarket.dao.MyDAO;
import kr.co.kmarket.entity.UserEntity;
import kr.co.kmarket.repository.UserRepo;
import kr.co.kmarket.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * 23/02/21 qna list
     * @autor 김재준
     * @param start
     * @param cate1
     * @return
     */
    public List<Cs_QnaVO> selectQnaArticles(int start, Integer cate1, String uid){
        return dao.selectQnaArticles(start, cate1, uid);
    }

    /**
     * 23/02/21 상품 리뷰 가져오기
     * @autor 김재준
     * @param revNo
     * @param start
     * @return
     */
    public List<product_reviewVO> selectReviews(Integer revNo, int start, Integer cate1, Integer cate2, String uid){
        return dao.selectReviews(revNo, start, cate1, cate2, uid);
    }

    /**
     * 23/02/21 리뷰란 상품명 클릭 시 product/view 하이퍼링크를 위한 상품 list
     * @autor 김재준
     * */
    public List<productVO> selectProducts(Integer cate1, Integer cate2, Integer start){
        return dao.selectProducts(cate1, cate2, start);
    }

    /*============================== 23/02/21 페이징 ==================================*/

    /**
     * 페이지 시작값
     * @autor 김재준
     * @param currentPage
     * @return
     */
    public int getLimitStart(int currentPage, int count){
        return (currentPage - 1) * count;
    }

    /**
     * 현재 페이지
     * @autor 김재준
     * @param pg
     * @return
     */
    public int getCurrentPage(String pg){
        int currentPage = 1;
        if (pg != null){
            currentPage = Integer.parseInt(pg);
        }
        return currentPage;
    }

    /**
     * 23/02/09 qna 전체 글 갯수 불러오기
     * @autor 김재준
     * @return
     */
    public long getTotalCount(Integer cate1){
        return dao.selectCountTotal(cate1);
    }

    /**
     * 23/02/21 상품 리뷰 total 값 불러오기
     * @autor 김재준
     * @return
     */
    public long getCountTotalForReview(Integer revNo){
        return dao.getCountTotalForReview(revNo);
    }

    /**
     * 마지막 페이지 번호
     * @autor 김재준
     * @param total
     * @return
     */
    public int getLastPageNum(long total, int count){
        int lastPage = 0;

        if(total == 0) return 1;

        if (total % count == 0){
            lastPage = (int) (total/count);
        }else{
            lastPage = (int) ((total/count) + 1);
        }
        if(lastPage == 0) lastPage = 1;
        return lastPage;
    }

    /**
     * 페이지 시작 번호
     * @autor 김재준
     * @param total
     * @param start
     * @return
     */
    public int getPageStartNum(long total, int start){
        return (int) (total - start);
    }

    /**
     * 페이지 그룹
     * @autor 김재준
     * @param currentPage
     * @param lastPage
     * @return
     */
    public int[] getPageGroup(int currentPage, int lastPage){
        int groupCurrent = (int) Math.ceil(currentPage/10.0);
        int groupStart = (groupCurrent - 1) * 10 + 1;
        int groupEnd = groupCurrent * 10;

        if (groupEnd > lastPage){
            groupEnd = lastPage;
        }

        int[] groups = {groupStart, groupEnd};

        return groups;
    }
    /*================================================================*/
    
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

    /**
     * 2023/02/22 // 심규영 // 마이페이지 홈 판매자 정보 불러오는 기능
     * @param uid
     * @return
     */
    public memberVO selectGetCompanyStatus(@Param("uid") String uid) {
        return dao.selectGetCompanyStatus(uid);
    }

    /**
     * 2023/02/22 // 심규영 // 마이페이지 홈 주문 상세 정보 불러오는 기능
     *      나오는 값
     *          ordDate     => 주문 날짜
     *          ordNo       => 주문 번호
     *          prodCate1   => 상품 이미지 불러오기용 카테1값
     *          prodCate2   => 상품 이미지 불러오기용 카테2값
     *          thumb1      => 상품 이미지 이름
     *          price       => 상품 개당 가격
     *          cPrice      => 상품 가격 * 주문한 상품 갯수
     *          disPrice    => 할인될 가격
     *          total       => 상품 하나당 전체 지불할 가격(할인 적용 됨)
     *          ordComplete => 주문 상태
     *          recipName   => 수취인 이름
     *          recipHp     => 수취인 번호
     *          recipZip    => 수취인 주소 우편 번호
     *          recipAddr1  => 수취인 주소
     *          recipAddr2  => 수취인 자세한 주소
     * @param ordNo
     * @return
     */
    public List<product_orderVO> selectGetOrderStatus(int ordNo) {
        return dao.selectGetOrderStatus(ordNo);
    }

    /**
     * 23/02/22 // 이해빈 // 쿠폰 가져오기
     */
    public List<CouponVO> selectCoupons(String uid){
        return dao.selectCoupons(uid);
    };

    /**
     * 23/02/22 // 이해빈 // 사용 가능한 쿠폰 갯수
     */
    public int getCouponCount(String uid){
        return dao.getCouponCount(uid);
    };

    // upload
    /**
     * 2023/02/22  // 이해빈 // 회원정보 수정
     * */
    public int updateMember(memberVO vo){
        return dao.updateMember(vo);
    }

    // delete

    // service
}
