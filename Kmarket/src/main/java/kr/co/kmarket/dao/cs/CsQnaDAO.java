package kr.co.kmarket.dao.cs;

import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CsQnaDAO {

    /**
     * 23/02/08 qna list
     * @autor 김재준
     * @param start
     * @return
     */
    public List<Cs_QnaVO> selectQnaArticles(@Param("start") int start, @Param("cate1") Integer cate1);

    /**
     * 23/02/10 qna list cate1Name 가져오기
     * @autor 김재준
     */
    public Cs_Cate1VO selectCate1Name(@Param("cate1") Integer cate1);


    /**
     * 23/02/08 qna list 작성글 선택
     * @autor 김재준
     * @param qnaNo
     * @return
     */
    public Cs_QnaVO selectQnaArticle(@Param("qnaNo") Integer qnaNo);

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
    public int selectCountTotal(@Param("cate1") Integer cate1);
}