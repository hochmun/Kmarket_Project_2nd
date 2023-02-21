package kr.co.kmarket.dao.cs;

import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_Cate2VO;
import kr.co.kmarket.vo.Cs_FaqVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CsFaqDAO {

    /* List */

    /**
     * 23/02/13 카테고리1 정보 가져오기
     * @autor 김재준
     * @return
     */
    public List<Cs_Cate1VO> selectCsCate1();

    /**
     * 23/02/13 카테1 값으로 카테2 정보 가져오기
     * @autor 김재준
     * @return
     */
    public List<Cs_Cate2VO> selectCsCate2(String csCate1);

    /**
     * 23/02/13 faq list 게시물 상위 10개 불러오기
     * @autor 김재준
     * @return
     */
    public List<Cs_FaqVO> selectCsFaqListWithCsCate1(@Param("faqCate1") Integer faqCate1,@Param("faqCate2") Integer faqCate2);

    /* View */

    /**
     * 23/02/13 faqNo로 게시물 상세보기
     * @autor 김재준
     * @return
     */
    public Cs_FaqVO selectCsFaqWithFaqNo(@Param("faqNo") Integer faqNo);
}