package kr.co.kmarket.service;

import kr.co.kmarket.dao.TermsPolicyDAO;
import kr.co.kmarket.vo.TermsPolicyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TermsPolicyService {

    @Autowired
    private TermsPolicyDAO dao;

    /**
     * termsPolicy 약관 가져오기
     * @since 23/02/21
     * @author 이해빈
     */
    public Map<Integer, List<TermsPolicyVO>> selectPolicy(int type){
        List<TermsPolicyVO> list = dao.selectPolicy(type);
        return list.stream().collect(Collectors.groupingBy(TermsPolicyVO::getTitleNum));
    }

    /**
     * termsPolicy 약관 카테고리 이름 가져오기
     * @since 23/02/21
     * @author 이해빈
     */
    public String getTypeName(int type){

        String typeName = "";

        switch(type){
            case 10:
                typeName = "구매회원 이용약관"; break;
            case 11:
                typeName = "판매회원 이용약관"; break;
            case 12:
                typeName = "개인정보처리방침"; break;
            case 13:
                typeName = "위치정보 이용약관"; break;
            case 14:
                typeName = "전자금융거래 이용약관"; break;
        }

        return typeName;
    }


}
