package kr.co.kmarket.dao;

import kr.co.kmarket.vo.member_termsVO;
import kr.co.kmarket.vo.memberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberDAO {
    public int insertMember(memberVO vo);
    public int insertSellerMember(memberVO vo);
    public member_termsVO selectTerms();
    public memberVO selectMember(String uid);
    public List<memberVO> selectMembers();
    public int updateMember(memberVO vo);
    public int deleteMember(String uid);
}
