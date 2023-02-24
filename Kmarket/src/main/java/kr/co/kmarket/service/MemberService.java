package kr.co.kmarket.service;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.repository.UserRepo;
import kr.co.kmarket.vo.member_termsVO;
import kr.co.kmarket.vo.memberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public String search_id(HttpServletResponse response, String name) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String id = dao.search_id(name);

        if(id == null){
            out.println("<script>");
            out.println("alert('가입된 아이디가 없습니다.');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
            return null;
        }else {
            return id;
        }
    }
    public int search_pw(memberVO vo){
        return dao.search_pw(vo);
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
