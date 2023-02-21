package kr.co.kmarket.service;

import kr.co.kmarket.dao.MyDAO;
import kr.co.kmarket.vo.Cs_QnaVO;
import kr.co.kmarket.vo.product_reviewVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    /**
     * 2023/02/21 // 심규영 // 마이서비스 dao 연결
     */
    @Autowired
    private MyDAO dao;

    // create

    // read

    /**
     * 23/02/21 qna list
     * @autor 김재준
     * @param start
     * @param cate1
     * @return
     */
    public List<Cs_QnaVO> selectQnaArticles(int start, Integer cate1){
        return dao.selectQnaArticles(start, cate1);
    }

    /**
     * 23/02/21 상품 리뷰 가져오기
     * @autor 김재준
     * @param revNo
     * @param start
     * @return
     */
    public List<product_reviewVO> selectReviews(Integer revNo, int start){
        return dao.selectReviews(revNo, start);
    }



    /*============================== 23/02/21 페이징 ==================================*/

    /**
     * 페이지 시작값
     * @autor 김재준
     * @param currentPage
     * @return
     */
    public int getLimitStart(int currentPage, int count){
        return (currentPage - 1) * count;
    }

    /**
     * 현재 페이지
     * @autor 김재준
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
    public long getTotalCount(Integer cate1){
        return dao.selectCountTotal(cate1);
    }

    /**
     * 23/02/21 상품 리뷰 total 값 불러오기
     * @autor 김재준
     * @return
     */
    public long getCountTotalForReview(Integer revNo){
        return dao.getCountTotalForReview(revNo);
    }

    /**
     * 마지막 페이지 번호
     * @autor 김재준
     * @param total
     * @return
     */
    public int getLastPageNum(long total, int count){
        int lastPage = 0;

        if(total == 0) return 1;

        if (total % count == 0){
            lastPage = (int) (total/count);
        }else{
            lastPage = (int) ((total/count) + 1);
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
    /*================================================================*/

    // upload

    // delete

    // service
}
