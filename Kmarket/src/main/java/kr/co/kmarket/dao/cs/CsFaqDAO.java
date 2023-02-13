package kr.co.kmarket.dao.cs;

import kr.co.kmarket.vo.Cs_Cate2VO;
import kr.co.kmarket.vo.Cs_FaqVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CsFaqDAO {

    public List<Cs_FaqVO> selectCsFaqListWithCsCate1(List<Cs_Cate2VO> vos);
}
