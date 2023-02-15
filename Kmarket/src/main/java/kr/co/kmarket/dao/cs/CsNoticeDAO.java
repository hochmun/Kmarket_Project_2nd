package kr.co.kmarket.dao.cs;

import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_NoticeVO;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CsNoticeDAO {

    /**
     * 23/02/08 notice list
     * @autor 김재준
     * @param start
     * @return
     */
    public List<Cs_NoticeVO> selectNotArticles(@Param("start") int start, @Param("noCate") Integer noCate);

    /**
     * 23/02/10 notice list cate1Name 가져오기
     * @autor 김재준
     */
    public Cs_NoticeVO selectNotCateName(@Param("noCate") Integer noCate);


    /**
     * 23/02/08 notice list 작성글 선택
     * @autor 김재준
     * @return
     */
    public Cs_NoticeVO selectNotArticle(@Param("noticeNo") Integer noticeNo);

    /**
     * 23/02/08 notice 글 count
     * @autor 김재준
     * @return
     */
    public int selectNotCountTotal(@Param("noCate") Integer noCate);
}
