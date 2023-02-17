package kr.co.kmarket.service;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.repository.UserRepo;
import kr.co.kmarket.vo.member_termsVO;
import kr.co.kmarket.vo.memberVO;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberDAO dao;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepo repo;

    /*일반회원가입*/
    public int insertMember(memberVO vo){
        vo.setPass(encoder.encode(vo.getPass1()));
        return dao.insertMember(vo);
    }
    /*판매자회원가입*/
    public int insertSellerMember(memberVO vo){
        vo.setPass(encoder.encode(vo.getPass1()));
        return dao.insertSellerMember(vo);
    }

    /*아이디찾기*/
    public int search_id(String name, String hp) throws Exception{
        return dao.search_id(name,hp);
    }

    public member_termsVO selectTerms(){
        return dao.selectTerms();
    }
    public memberVO selectMember(String uid){
        return dao.selectMember(uid);
    }
    public List<memberVO> selectMembers(){
        return dao.selectMembers();
    }
    public int updateMember(memberVO vo){
        return dao.updateMember(vo);
    }
    public int deleteMember(String uid){
        return dao.deleteMember(uid);
    }
    public int countByUid(String uid){
        return repo.countByUid(uid);
    }

}
