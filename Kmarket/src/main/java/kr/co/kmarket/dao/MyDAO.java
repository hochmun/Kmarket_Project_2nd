package kr.co.kmarket.dao;

import kr.co.kmarket.vo.*;
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
    public List<Cs_QnaVO> selectQnaArticles(@Param("start") int start, @Param("cate1") Integer cate1, @Param("uid") String uid);

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
    public List<product_reviewVO> selectReviews(@Param("revNo") Integer revNo, @Param("start") int start
            , @Param("cate1") Integer cate1, @Param("cate2") Integer cate2, @Param("uid") String uid);

    /**
     * 23/02/21 상품 리뷰 total 값 가져오기
     * @author 김재준
     * @param revNo
     * */
    public int getCountTotalForReview(@Param("revNo") Integer revNo);

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

    // upload
    /**
     * 2023/02/22  // 이해빈 // 회원정보 수정
     * */
    public int updateMember(memberVO vo);

    // delete

    // service
}
