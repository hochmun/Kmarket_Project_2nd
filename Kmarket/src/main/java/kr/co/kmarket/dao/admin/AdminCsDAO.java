package kr.co.kmarket.dao.admin;

import kr.co.kmarket.vo.AdminCsVo;
import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_Cate2VO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @since 2023/02/13 // 심규영 // 관리자 고객센터 DAO 생성
 */
@Mapper
@Repository
public interface AdminCsDAO {
    // create
    public void createCsArticle();

    // read
    public void selectCsArticle();

    /**
     * 2023/02/15 // 심규영 // 게시물 불러오기
     * @param map
     *      table => 테이블 이름
     *      cate => faq, qna, notice
     *      c1Name => faqCate1, qnaCate1
     *      cate1 => 카테고리 번호
     *      c2Name => faqCate2, qnaCate2
     *      cate2 => 카테고리 번호
     *      type => notice 카테 번호
     *      noName => faqNo, qnaNo, noticeNo
     *      start => 페이지 시작값(faq는 1 고정)
     * @return
     */
    public List<AdminCsVo> selectCsArticles(Map<String, String> map);

    /**
     * 2023/02/14 // 심규영 // 게시물 총 갯수 계산, 최초 접속시 계산
     * @param map
     *      noName = 게시물이름 + No
     *      table = 불러올 테이블 이름
     *      cate = 게시물 종류
     *      type = notice에서 카테고리
     *      cate1 = csCate1 값
     *      cate2 = csCate2 값
     */
    public int selectCountCsArticle(Map<String, String> map);

    /**
     * 2023/02/15 // 심규영 // 고객센터 카테고리1 리스트 불러오기
     * @return
     */
    public List<Cs_Cate1VO> selectCsCate1s();

    /**
     * 2023/02/15 // 심규영 // 고객센터 카테고리1값으로 카테고리2 리스트 불러오기
     * @param cate1
     * @return
     */
    public List<Cs_Cate2VO> selectCsCate2sWithCate1(int cate1);

    // upload
    public void uploadCsArticle();

    // delete
    public void deleteCsArticle();

}
