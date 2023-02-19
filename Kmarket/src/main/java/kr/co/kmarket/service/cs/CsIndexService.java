package kr.co.kmarket.service.cs;

import kr.co.kmarket.dao.cs.CsIndexDAO;
import kr.co.kmarket.dao.cs.CsNoticeDAO;
import kr.co.kmarket.vo.Cs_NoticeVO;
import kr.co.kmarket.vo.Cs_QnaVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CsIndexService {

    @Autowired
    public CsIndexDAO dao;


    public List<Cs_NoticeVO> selectCsNoticeListLimit5(){
        return dao.selectCsNoticeListLimit5();
    }

    public List<Cs_QnaVO> selectCsQnaListLimit5(){
        return dao.selectCsQnaListLimit5();
    }
}
