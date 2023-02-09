package kr.co.kmarket.dao.cs;

import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CsQnaDAO {
    /**
     * 23/02/08 qna list 작성글 목록 불러오기
     * @author 김재준
     * @param start
     * @return
     */
    public List<Cs_QnaVO> selectQnaArticle(int start);

    /**
     * 23/02/08 qna list 작성글 선택
     * @autor 김재준
     * @param no
     * @return
     */
    public Cs_QnaVO selectCsQnaNo(int no);

    /**
     * 23/02/08 qna 글 작성
     * @autor 김재준
     * @param vo
     * @return
     */
    public int insertQnaArticle(Cs_QnaVO vo);

    /**
     * 23/02/08 qna 글 수정
     * @autor 김재준
     * @param vo
     * @return
     */
    public int updateQnaArticle(Cs_QnaVO vo);

    /**
     * 23/02/08 qna 글 삭제
     * @autor 김재준
     * @param no
     * @return
     */
    public int deleteQnaArticle(int no);

    /**
     * 23/02/08 qna 글 count
     * @return
     */
    public int selectCountTotal();


    /**
     * 23/02/09 카테고리 이름 불러오기
     * @param cate1
     * @return
     */
    public Cs_Cate1VO selectCsCate(String cate1);
}
