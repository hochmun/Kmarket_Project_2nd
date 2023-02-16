package kr.co.kmarket.service.cs;

import kr.co.kmarket.dao.cs.CsNoticeDAO;
import kr.co.kmarket.vo.Cs_NoticeVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CsNoticeService {

    @Autowired
    public CsNoticeDAO dao;

    /**
     * 23/02/08 notice list
     * @autor 김재준
     * @param start
     * @return
     */
    public List<Cs_NoticeVO> selectNotArticles(@Param("start") int start){
        return dao.selectNotArticles(start);
    }

    /**
     * 23/02/08 notice list 작성글 선택
     * @autor 김재준
     * @return
     */
    public Cs_NoticeVO selectNotArticle(@Param("noticeNo") Integer noticeNo){
        return dao.selectNotArticle(noticeNo);
    }


    /* 23/02/09 페이징 */

    /**
     * 페이지 시작값
     * @param currentPage
     * @return
     */
    public int getLimitStart(int currentPage){
        return (currentPage - 1) * 10;
    }

    /**
     * 현재 페이지
     * @param pg
     * @return
     */
    public int getCurrentPage(String pg){
        int currentPage = 1;
        if (pg != null){
            currentPage = Integer.parseInt(pg);
        }
        return currentPage;
    }

    /**
     * 23/02/09 qna 전체 글 갯수 불러오기
     * @autor 김재준
     * @return
     */
    public long getTotalCount(){
        return dao.selectNotCountTotal();
    }

    /**
     * 마지막 페이지 번호
     * @param total
     * @return
     */
    public int getLastPageNum(long total){
        int lastPage = 0;

        if (total % 10 == 0){
            lastPage = (int) (total/10);
        }else{
            lastPage = (int) ((total/10) + 1);
        }
        return lastPage;
    }

    /**
     * 페이지 시작 번호
     * @autor 김재준
     * @param total
     * @param start
     * @return
     */
    public int getPageStartNum(long total, int start){
        return (int) (total - start);
    }

    /**
     * 페이지 그룹
     * @autor 김재준
     * @param currentPage
     * @param lastPage
     * @return
     */
    public int[] getPageGroup(int currentPage, int lastPage){
        int groupCurrent = (int) Math.ceil(currentPage/10.0);
        int groupStart = (groupCurrent - 1) * 10 + 1;
        int groupEnd = groupCurrent * 10;

        if (groupEnd > lastPage){
            groupEnd = lastPage;
        }

        int[] groups = {groupStart, groupEnd};

        return groups;
    }
}