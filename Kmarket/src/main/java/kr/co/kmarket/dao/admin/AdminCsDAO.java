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

    /**
     * 2023/02/16 // 심규영 // 관리자 고객센터 게시물 작성
     * 넣는 값
     *      table => 테이블 이름
     *      cate => 카테고리(notice, faq)
     *      title => 게시물 제목
     *      content => 게시물 내용
     *      regip => ip번호
     *      cate1 => faq 카테고리1 번호
     *      cate2 => faq 카테고리2 번호
     *      type => notice 카테고리 번호
     */
    public int createCsArticle(Map<String, String> map);

    // read

    /**
     * 2023/02/16 // 심규영 // 관리자 고객센터 글 보기
     * 들어가는 map 정보
     *      table => 테이블 이름
     *      typeNo => No 속성 이름
     *      no => 게시물 번호
     *      cate => 카테고리 이름
     *      
     *      if (cate == faq || cate == qna)
     *      c1Name => 카테고리1 속성 이름
     *      c2Name => 카테고리2 속성 이름
     * @param map
     */
    public AdminCsVo selectCsArticle(Map<String, String> map);

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
     * 2023/02/17 // 심규영 // 게시물 자신(no)을 제외한 총 갯수 계산
     * 들어가는 값
     *      cate1 => 수정 하는 게시물의 카테고리1 값
     *      cate2 => 수정 하는 게시물의 카테고리2 값
     *      no    => 수정 하는 게시물의 게시물 번호
     * @return
     */
    public int selectCountCsFaqArticleWithNo(Map<String, String> map);

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

    /**
     * 2023/02/17 // 심규영 // 관리자 고객센터 글 수정 기능
     * 들어가는 값
     *      cate    => 카테고리 이름(faq, notice)
     *      no      => 수정하는 게시물 번호
     *      title   => 수정되는 게시물 제목
     *      content => 수정되는 게시물 내용
     *
     *      // cate 가 faq 일때
     *      cate1   => 수정되는 faq 카테고리1 번호
     *      cate2   => 수정되는 faq 카테고리2 번호
     *
     *      // cate 가 notice 일때
     *      type    => 수정되는 notice 카테고리 번호
     */
    public int uploadCsArticle(Map<String, String> map);

    /**
     * 2023/02/16 // 심규영 // 관리자 고객센터 문의하기 답변
     * 들어오는 값
     *      content => 관리자 답변 내용
     *      no      => 게시물 번호
     */
    public int updateQnaArticle(@Param("content") String content,@Param("no") String no);

    // delete

    /**
     * 2023/02/16 // 심규영 // 관리자 고객센터 게시글 삭제 메소드
     * map에 들어오는 값
     *      table   => 테이블 이름
     *      cateNo  => 해당 테이블 No 속성 이름
     *      no      => 게시물 번호
     * @param map
     */
    public int deleteCsArticle(Map<String, String> map);

}
