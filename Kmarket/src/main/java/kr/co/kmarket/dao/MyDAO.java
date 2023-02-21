package kr.co.kmarket.dao;

import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_QnaVO;
import kr.co.kmarket.vo.product_reviewVO;
import kr.co.kmarket.vo.member_pointVO;
import kr.co.kmarket.vo.product_orderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MyDAO {
    // create

    // read

    /**
     * 23/02/21 qna list
     * @autor 김재준
     * @param start
     * @param cate1
     */
    public List<Cs_QnaVO> selectQnaArticles(@Param("start") int start, @Param("cate1") Integer cate1);

    /**
     * 23/02/21 qna 글 count
     * @autor 김재준
     * @param cate1
     */
    public int selectCountTotal(@Param("cate1") Integer cate1);

    /**
     * 23/02/21 상품 리뷰 가져오기
     * @autor 김재준
     * @param revNo
     * @param start
     * */
    public List<product_reviewVO> selectReviews(@Param("revNo") Integer revNo, @Param("start") int start);

    /**
     * 23/02/21 상품 리뷰 total 값 가져오기
     * @author 김재준
     * @param revNo
     * */
    public int getCountTotalForReview(@Param("revNo") Integer revNo);

    /**
     * 2023/02/21 // 심규영 // 마이페이지 홈 최근 주문 내역 5개 불러오는 기능
     * @param uid
     */
    public List<product_orderVO> selectMyOrder(@Param("uid") String uid);

    /**
     * 2023/02/21 // 심규영 // 마이페이지 홈 최근 포인트 적립 내역 5개 불러오는 기능
     */
    public List<member_pointVO> selectMyPointList5(@Param("uid") String uid);

    /**
     * 2023/02/21 // 심규영 // 마이페이지 홈 최근 문의 내역 5개 불러오는 기능
     * @param uid
     * @return
     */
    public List<Cs_QnaVO> selectMyQnaList5(@Param("uid") String uid);

    // upload

    // delete

    // service
}
