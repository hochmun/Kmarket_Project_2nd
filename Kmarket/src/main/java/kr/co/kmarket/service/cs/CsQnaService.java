package kr.co.kmarket.service.cs;

import kr.co.kmarket.dao.cs.CsQnaDAO;
import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_QnaVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CsQnaService {

    @Autowired
    private CsQnaDAO dao;

    /**
     * 23/02/08 qna list 전체 작성글 불러오기
     * @author 김재준
     * @param start
     * @return
     */
    public List<Cs_QnaVO> selectQnaArticle(int start){
        return dao.selectQnaArticle(start);
    }

    /**
     * 23/02/08 qna list 작성글 선택
     * @autor 김재준
     * @param no
     * @return
     */
    public Cs_QnaVO selectCsQnaNo(int no){
        return dao.selectCsQnaNo(no);
    }

    /**
     * 23/02/08 qna 글 작성
     * @autor 김재준
     * @param vo
     * @return
     */
    public int insertQnaArticle(Cs_QnaVO vo){
        // 글 작성
        int result = dao.insertQnaArticle(vo);
        return result;
    }

    /**
     * 23/02/08 qna 글 수정
     * @autor 김재준
     * @param vo
     * @return
     */
    public int updateQnaArticle(Cs_QnaVO vo){
        int result = dao.updateQnaArticle(vo);
        return result;
    }

    /**
     * 23/02/08 qna 글 삭제
     * @autor 김재준
     * @param no
     * @return
     */
    public int deleteQnaArticle(int no){
        return dao.deleteQnaArticle(no);
    }


    /* 페이징 */

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
     * 전체 게시물 갯수
     * @return
     */
    public long getTotalCount(){
        return dao.selectCountTotal();
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
     * @param total
     * @param start
     * @return
     */
    public int getPageStartNum(long total, int start){
        return (int) (total - start);
    }

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

    public Cs_Cate1VO selectCsCate(String cate1){
        return dao.selectCsCate(cate1);
    }
}
