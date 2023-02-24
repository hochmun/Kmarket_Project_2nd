package kr.co.kmarket.dao;

import kr.co.kmarket.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface MyDAO {
    // create

    /**
     * 2023/02/23 // 심규영 // 문의하기 작성 기능
     * @param vo
     * @return
     */
    public int insertProductQna(Product_qnaVO vo);

    /**
     * 2023/02/23 // 심규영 // 상품 수취 확인 시 포인트 적립 기록
     * @param ordNo
     * @param uid
     * @return
     */
    public int insertSaveMemberPoint(@Param("ordNo") String ordNo, @Param("uid") String uid);

    /**
     * 2023/02/23 // 심규영 // 리뷰 작성 기능
     * <p>
     *     들어가는 값(prodNo:상품번호,ordNo:주문번호,review:리뷰내용,uid:작성자아이디,starRating:별점,regip:작성주소)
     * </p>
     * @param data
     * @return
     */
    public int insertProductReview(Map<String, String> data);

    // read

    /**
     * 23/02/21 qna list
     * @autor 김재준
     * @param start
     * @param cate1
     */
    public List<Cs_QnaVO> selectQnaArticles(@Param("start") int start, @Param("cate1") Integer cate1, @Param("uid") String uid);

    /**
     * 23/02/21 qna 글 count
     * @autor 김재준
     * @param cate1
     */
    public int selectCountTotal(@Param("cate1") Integer cate1, @Param("uid") String uid);

    /**
     * 23/02/21 상품 리뷰 가져오기
     * @autor 김재준
     * @param revNo
     * @param start
     * */
    public List<product_reviewVO> selectReviews(@Param("revNo") Integer revNo, @Param("start") int start
            , @Param("cate1") Integer cate1, @Param("cate2") Integer cate2, @Param("uid") String uid);

    /**
     * 23/02/21 상품 리뷰 total 값 가져오기
     * @author 김재준
     * */
    public int getCountTotalForReview(@Param("uid") String uid);

    /**
     * 2023/02/22 포인트 내역 불러오기
     * @autor 김재준
     */
    public List<member_pointVO> selectMyPoint(@Param("uid") String uid, @Param("start") int start, @Param("date") String date);

    /**
     * 23/02/22 전체 포인트 내역 갯수 불러오기
     * @autor 김재준
     */
    public int getCountTotalForPoint(@Param("uid") String uid);

    /**
     * 23/02/23 포인트내역 기간별 출력
     * @autor 김재준
     */
    public List<member_pointVO> searchDateMyPoint(Map<String, String> map);

    /**
     * 2023/02/22 // 김재준 // 주문 내역 불러오기
     * @param uid
     */
    public List<product_orderVO> selectMyOrdered(@Param("uid") String uid, @Param("start") int start, @Param("date") String date);

    /**
     * 23/02/22 전체주문내역 기간별 출력
     * @autor 김재준
     */
    public List<product_orderVO> searchDateMyOrder(Map<String, String> map);

    /**
     * 23/02/22 전체 주문내역 갯수 불러오기
     * @autor 김재준
     */
    public int getCountTotalForOrder(@Param("uid") String uid);

    /**
     * 23/02/21 리뷰란 상품명 클릭 시 product/view 하이퍼링크를 위한 상품 list
     * @autor 김재준
     * */
    public List<productVO> selectProducts(@Param("cate1") Integer cate1, @Param("cate2") Integer cate2,
                                          @Param("start") int start);

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
     * 2023/02/24 // 심규영 // 마이페이지 홈 최근 리뷰 내역 5개 불러오는 기능
     * @param uid
     */
    public List<product_reviewVO> selectMyReviewList5(@Param("uid") String uid);

    /**
     * 2023/02/21 // 심규영 // 마이페이지 홈 최근 문의 내역 5개 불러오는 기능
     * @param uid
     * @return
     */
    public List<Cs_QnaVO> selectMyQnaList5(@Param("uid") String uid);

    /**
     * 2023/02/22 // 심규영 // 마이페이지 홈 판매자 정보 불러오는 기능
     * @param uid
     * @return
     */
    public memberVO selectGetCompanyStatus(@Param("uid") String uid);

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
    public List<product_orderVO> selectGetOrderStatus(@Param("ordNo") int ordNo);

    /**
     * 23/02/22 // 이해빈 // 쿠폰 가져오기
     */
    public List<CouponVO> selectCoupons(String uid);

    /**
     * 23/02/22 // 이해빈 // 사용 가능한 쿠폰 갯수
     */
    public int getCouponCount(String uid);

    // upload
    /**
     * 2023/02/22  // 이해빈 // 회원정보 수정
     * */
    public int updateMember(memberVO vo);

    /**
     * 2023/02/22 // 심규영 // 마이페이지 수취 확인시 상품 주문 과 멤버 포인트 적립
     * @param ordNo
     */
    public int updateProductOrderAndPoint(@Param("ordNo") String ordNo, @Param("uid") String uid);

    /**
     * 2023/02/23 // 심규영 // 상품 별점 점수 및 리뷰 갯수 수정
     * <p>
     *     필요한 값(starRating:별점, prodNo:상품번호)
     * </p>
     * @param data
     */
    public int updateProductScoreAndReivew(Map<String, String> data);

    // delete

}
