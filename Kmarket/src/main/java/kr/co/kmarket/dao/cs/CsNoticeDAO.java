package kr.co.kmarket.dao.cs;

import kr.co.kmarket.vo.Cs_NoticeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CsNoticeDAO {

    /**
     * 23/02/16 notice list all 불러오기
     * @autor 김재준
     * @param start
     * @return
     */
    public List<Cs_NoticeVO> selectNotArticlesAll(@Param("start") int start);


    /**
     * 23/02/08 notice list 카테고리별 불러오기
     * @autor 김재준
     * @param start
     * @return
     */
    public List<Cs_NoticeVO> selectNotArticles(@Param("start") int start,@Param("cate1") String noCate1);


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
    public int selectNotCountTotal(String noCate);
}
