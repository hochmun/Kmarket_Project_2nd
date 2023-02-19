package kr.co.kmarket.dao.cs;

import kr.co.kmarket.vo.Cs_NoticeVO;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CsIndexDAO {

    public List<Cs_NoticeVO> selectCsNoticeListLimit5();

    public List<Cs_QnaVO> selectCsQnaListLimit5();
}
