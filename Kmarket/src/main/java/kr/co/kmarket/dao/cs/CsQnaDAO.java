package kr.co.kmarket.dao.cs;

import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CsQnaDAO {

    /**
     * 23/02/08 qna list 작성글 전체 번호순 불러오기
     * @author 김재준
     * @param start
     * @return
     */
    public List<Cs_QnaVO> selectQnaArticles(int start, String cate1) ;

    /**
     * 23/02/08 qna list 작성글 선택
     * @autor 김재준
     * @param qnaNo
     * @return
     */
    public Cs_QnaVO selectQnaArticle(int qnaNo);

    /**
     * 23/02/09 qna no로 글 선택
     * @autor 김재준
     * @param qnaNo
     * @return
     */
    public Cs_QnaVO selectCsQnaNo(int qnaNo);

    /**
     * 23/02/08 qna 글 작성
     * @autor 김재준
     * @param vo
     * @return
     */
    public int insertQnaArticle(Cs_QnaVO vo);

    /**
     * 23/02/08 qna 글 count
     * @autor 김재준
     * @return
     */
    public int selectCountTotal(String cate1);


    /**
     * 23/02/09 qna 카테고리1 불러오기 (전체)
     * @autor 김재준
     * @param cate1
     * @return
     */
    public Cs_Cate1VO selectCate1(String cate1);
}