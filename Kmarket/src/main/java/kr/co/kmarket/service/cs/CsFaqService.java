package kr.co.kmarket.service.cs;

import kr.co.kmarket.dao.cs.CsFaqDAO;
import kr.co.kmarket.vo.Cs_Cate1VO;
import kr.co.kmarket.vo.Cs_Cate2VO;
import kr.co.kmarket.vo.Cs_FaqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CsFaqService {

    @Autowired
    public CsFaqDAO dao;

    /* List */

    /**
     * 23/02/13 카테고리1 정보 가져오기
     * @autor 김재준
     * @return
     */
    public List<Cs_Cate1VO> selectCsCate1() {
        return dao.selectCsCate1();
    }

    /**
     * 23/02/13 카테1 값으로 카테2 정보 가져오기
     * @autor 김재준
     * @return
     */
    public List<Cs_Cate2VO> selectCsCate2(String csCate1) {
        return dao.selectCsCate2(csCate1);
    }

    /**
     * 23/02/13 faq list (cate1값으로 리스트 불러오기)
     * @autor 김재준
     * @return
     */
    public List<Cs_FaqVO> selectCsFaqListWithCsCate1 (Integer faqCate1, Integer faqCate2) {
        return dao.selectCsFaqListWithCsCate1(faqCate1, faqCate2);
    }

    /* View */

    /**
     * 23/02/13 faqNo로 게시물 상세보기
     * @autor 김재준
     * @return
     */
    public Cs_FaqVO selectCsFaqWithFaqNo(Integer faqNo) {
        return dao.selectCsFaqWithFaqNo(faqNo);
    }

}