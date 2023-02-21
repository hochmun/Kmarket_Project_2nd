package kr.co.kmarket.dao;

import kr.co.kmarket.vo.TermsPolicyVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TermsPolicyDAO {

    /**
     * termsPolicy 약관 가져오기
     * @since 23/02/21
     * @author 이해빈
     */
    public List<TermsPolicyVO> selectPolicy(@Param("type") int type);

}
