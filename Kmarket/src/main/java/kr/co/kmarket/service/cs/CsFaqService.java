package kr.co.kmarket.service.cs;

import kr.co.kmarket.dao.cs.CsFaqDAO;
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

    /**
     * 23/02/13 faq list (cate1값으로 리스트 불러오기)
     * @autor 김재준
     * @return
     */
    public List<Cs_FaqVO> selectCsFaqListWithCsCate1(List<Cs_Cate2VO> vos){
        return dao.selectCsFaqListWithCsCate1(vos);
    }
}
