package kr.co.kmarket.service.admin;

import kr.co.kmarket.dao.admin.AdminCsDAO;
import kr.co.kmarket.dto.AdminCsListParamDTO;
import kr.co.kmarket.dto.PagingDTO;
import kr.co.kmarket.util.PagingUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 2023/02/14 // 심규영 // 서비스 생성
 */
@Slf4j
@Service
public class AdminCsService {

    /**
     * 2023/02/15 // 심규영 // dao 연결
     */
    @Autowired
    private AdminCsDAO dao;
    
    // create

    // read

    /**
     * 2023/02/15 // 심규영 // 게시물 총 갯수 계산, 최초 접속시 계산
     *      noName = 게시물이름 + No
     *      table = 불러올 테이블 이름
     *      cate = 게시물 종류
     *      type = notice에서 카테고리
     *      cate1 = csCate1 값
     *      cate2 = csCate2 값
     */
    public int selectCountCsArticle(String cate, AdminCsListParamDTO param) {
        Map<String, String> map = new HashMap<>();
        
        // hashmap에 값 입력하기
        map.put("noName", cate+"No");
        map.put("table", "km_cs_"+cate);
        map.put("cate", cate);

        if(cate.equals("notice")) {
            if(param.getType().equals("")) map.put("type", "%%");
            else map.put("type", param.getType());
        }
        if(cate.equals("qna")) {
            if(param.getCate1().equals("")) map.put("cate1", "%%");
            else map.put("cate1", param.getCate1());

            if(param.getCate2().equals("")) map.put("cate2", "%%");
            else map.put("cate2", param.getCate2());
        }

        return dao.selectCountCsArticle(map);
    }

    // update

    // delete

    // service

    /**
     * 2023/02/14 // 심규영 // 관리자 고객센터 리스트 종합 처리
     *
     * @return
     */
    public Map<String, Object> CsListDataProcess(String cate, AdminCsListParamDTO param) {
        // 데이터를 담을 map
        Map<String, Object> data = new HashMap<>();

        // param 처리
        paramProcess(param);
        
        // 페이징 처리
        PagingDTO paging = new PagingUtil().getPagingDTO(param.getPg(), selectCountCsArticle(cate, param));
        data.put("paging", paging);

        // 카테고리에 따른 목록 불러서 map에 넣기

        data.put("etcText", param);

        return data;
    }

    public void paramProcess(AdminCsListParamDTO param) {
        if(param.getPg() == null) param.setPg("");
        if(param.getType() == null) param.setType("");
        if(param.getCate1() == null) param.setCate1("");
        if(param.getCate2() == null) param.setCate2("");
    }

}
