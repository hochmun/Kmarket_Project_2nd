package kr.co.kmarket.service.admin;

import kr.co.kmarket.dao.admin.AdminCsDAO;
import kr.co.kmarket.dto.AdminCsListParamDTO;
import kr.co.kmarket.dto.PagingDTO;
import kr.co.kmarket.service.MainService;
import kr.co.kmarket.util.PagingUtil;
import kr.co.kmarket.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
    /**
     * 2023/02/16 // 심규영 // 관리자 고객센터 게시물 작성
     * 넣는 값
     *      table => 테이블 이름
     *      cate => 카테고리(notice, faq)
     *      title => 게시물 제목
     *      content => 게시물 내용
     *      regip => ip번호
     *      cate1 => faq 카테고리1 번호
     *      cate2 => faq 카테고리2 번호
     *      type => notice 카테고리 번호
     */
    public int createCsArticle(Map<String, String> map, String cate) {
        map.put("cate", cate); // 카테고리
        map.put("table", "km_cs_"+cate); // 테이블 이름
        // regip는 controller에서 처리중

        return dao.createCsArticle(map);
    }

    // read

    /**
     * 2023/02/16 // 심규영 // 관리자 고객센터 문의하기 답변 <br>
     * 들어오는 map(map) 정보 <br>
     *      pg      => 게시물이 있던 페이지 번호 <br>
     *      no      => 게시물 번호 <br>
     * 들어가는 map(data) 정보 <br>
     *      table   => 테이블 이름 <br>
     *      typeNo  => No 속성 이름 <br>
     *      no      => 게시물 번호 <br>
     *      cate    => 카테고리 이름 <br>
     *
     *      if (cate == faq || cate == qna) <br>
     *      c1Name  => 카테고리1 속성 이름 <br>
     *      c2Name  => 카테고리2 속성 이름
     * @param map
     */
    public AdminCsVo selectCsArticle(Map<String, String> map, String cate) {
        Map<String, String> data = new HashMap<>();
        
        // 데이터 입력
        data.put("table", "km_cs_"+cate);
        data.put("typeNo", cate+"No");
        data.put("no", map.get("no"));
        data.put("cate", cate);

        if(cate.equals("faq") || cate.equals("qna")) {
            data.put("c1Name", cate+"Cate1");
            data.put("c2Name", cate+"Cate2");
        }

        return dao.selectCsArticle(data);
    }

    /**
     * 2023/02/15 // 심규영 // 게시물 불러오기
     *      table => 테이블 이름
     *      cate => faq, qna, notice
     *      c1Name => faqCate1, qnaCate1
     *      cate1 => 카테고리 번호
     *      c2Name => faqCate2, qnaCate2
     *      cate2 => 카테고리 번호
     *      type => notice 카테 번호
     *      noName => faqNo, qnaNo, noticeNo
     *      start => 페이지 시작값(faq는 1 고정)
     * @return
     */
    public List<AdminCsVo> selectCsArticles(String cate, int start, AdminCsListParamDTO param) {
        Map<String, String> map = new HashMap<>();

        // 공통
        map.put("table", "km_cs_"+cate);
        map.put("cate", cate);
        map.put("noName", cate+"No");

        // 자주묻는 질문, 문의 하기 카테고리 입력
        if(cate.equals("faq") || cate.equals("qna")) {
            map.put("c1Name", cate+"Cate1");
            map.put("c2Name", cate+"Cate2");

            if(param.getCate1().equals("") || param.getCate1() == null) map.put("cate1", "%%");
            else map.put("cate1", param.getCate1());
            if(param.getCate2().equals("") || param.getCate2() == null) map.put("cate2", "%%");
            else map.put("cate2", param.getCate2());
        }

        // 공지사항 타입 입력
        if(cate.equals("notice")) {
            if(param.getType().equals("") || param.getType() == null) map.put("type", "%%");
            else map.put("type", param.getType());
        }

        // 자주묻는 질문은 페이지 시작 번호 0로 고정
        if(cate.equals("faq")) map.put("start", "0");
        else map.put("start", start+"");

        // 출력
        return dao.selectCsArticles(map);
    }

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
        if(cate.equals("qna") || cate.equals("faq")) {
            if(param.getCate1().equals("")) map.put("cate1", "%%");
            else map.put("cate1", param.getCate1());

            if(param.getCate2().equals("")) map.put("cate2", "%%");
            else map.put("cate2", param.getCate2());
        }

        return dao.selectCountCsArticle(map);
    }

    /**
     * 2023/02/17 // 심규영 // 게시물 자신(no)을 제외한 총 갯수 계산
     * 들어가는 값
     *      cate1 => 수정 하는 게시물의 카테고리1 값
     *      cate2 => 수정 하는 게시물의 카테고리2 값
     *      no    => 수정 하는 게시물의 게시물 번호
     * @return
     */
    public int selectCountCsFaqArticleWithNo(Map<String, String> map) {
        Map<String, String> data = new HashMap<>();

        data.put("no", map.get("no"));
        data.put("cate1", map.get("cate1"));
        data.put("cate2", map.get("cate2"));

        return dao.selectCountCsFaqArticleWithNo(data);
    }

    /**
     * 2023/02/15 // 심규영 // 고객센터 카테고리1 리스트 불러오기
     * @return
     */
    public List<Cs_Cate1VO> selectCsCate1s(){
        return dao.selectCsCate1s();
    }

    /**
     * 2023/02/15 // 심규영 // 고객센터 카테고리1값으로 카테고리2 리스트 불러오기
     * @return
     */
    public List<Cs_Cate2VO> selectCsCate2sWithCate1(String cate1Str){
        int cate1 = Integer.parseInt(cate1Str);
        return dao.selectCsCate2sWithCate1(cate1);
    }

    // update

    /**
     * 2023/02/17 // 심규영 // 관리자 고객센터 글 수정 기능
     * 들어가는 값
     *      cate    => 카테고리 이름(faq, notice)
     *      no      => 수정하는 게시물 번호
     *      title   => 수정되는 게시물 제목
     *      content => 수정되는 게시물 내용
     *
     *      // cate 가 faq 일때
     *      cate1   => 수정되는 faq 카테고리1 번호
     *      cate2   => 수정되는 faq 카테고리2 번호
     *
     *      // cate 가 notice 일때
     *      type    => 수정되는 notice 카테고리 번호
     */
    public int uploadCsArticle(Map<String, String> map, String cate) {
        Map<String, String> data = new HashMap<>();

        data.put("cate", cate);
        data.put("no", map.get("no"));
        data.put("title", map.get("title"));
        data.put("content", map.get("content"));

        if(cate.equals("faq")) {
            data.put("cate1", map.get("cate1"));
            data.put("cate2", map.get("cate2"));
        }

        if(cate.equals("notice")) {
            data.put("type", map.get("type"));
        }

        return dao.uploadCsArticle(data);
    }

    /**
     * 2023/02/16 // 심규영 // 관리자 고객센터 문의하기 답변
     * 들어오는 값
     *      content => 관리자 답변 내용
     *      no      => 게시물 번호
     */
    public int updateQnaArticle(String content, String no) {
        return dao.updateQnaArticle(content, no);
    }

    // delete

    /**
     * 2023/02/16 // 심규영 // 관리자 고객센터 게시글 삭제 메소드
     * map에 들어오는 값
     *      table   => 테이블 이름
     *      cateNo  => 해당 테이블 No 속성 이름
     *      no      => 게시물 번호
     */
    public int deleteCsArticle(String no, String cate) {
        Map<String, String> data = new HashMap<>();

        data.put("table", "km_cs_"+cate);
        data.put("cateNo", cate+"No");
        data.put("no", no);

        return dao.deleteCsArticle(data);
    }

    // service

    /**
     * 2023/02/16 // 심규영 // 관리자 고객센터 글 쓰기 종합 처리
     */
    public Map<String, Object> CsWriteDataProcess(String cate) {
        // 데이터를 담을 map
        Map<String, Object> data = new HashMap<>();

        // cate가 faq일 경우 cs_cate1리스트 가져오기
        if(cate.equals("faq")) {
            List<Cs_Cate1VO> cate1VOs = selectCsCate1s();
            data.put("cate1VOs", cate1VOs);
        }

        return data;
    }

    /**
     * 2023/02/16 // 심규영 // 관리자 고객센터 글 쓰기 포스트 처리
     * map에 들어 있을 수 있는 값
     *      cate1 => faq 글 작성시 오는 값
     *      cate2 => cs_cate2
     *      type => notice 카테고리
     *      title => 제목
     *      contents => 내용
     */
    public int CsWritePostProcess(Map<String, String> map, String cate) {
        int result = 0;

        // 글 작성 전 cate가 faq일 경우
        // cate1과 cate2값으로 글 갯수 카운트
        // 글 갯수가 10개 이상일 경우 -1리턴
        if(cate.equals("faq")) {
            AdminCsListParamDTO param = AdminCsListParamDTO.builder().cate1(map.get("cate1")).cate2(map.get("cate2")).build();
            int total = selectCountCsArticle(cate, param);

            if(total >= 10) {
                result = -1;
                return result;
            }
        }

        // 글 작성
        result = createCsArticle(map, cate);

        return result;
    }

    public int CsModifyPostProcess(String cate, Map<String, String> etcText) {
        int result = 0;

        // cate가 faq일 경우 cate1의 cate2 게시물 갯수 확인
        // 해당 게시물(no)를 제외한 게시물 갯수가 10개 이상이면 수정 실패(-1 리턴)
        if(cate.equals("faq")) {
            int total = selectCountCsFaqArticleWithNo(etcText);

            if(total >= 10) {
                result = -1;
                return result;
            }
        }

        // 글 수정
        result = uploadCsArticle(etcText, cate);

        return result;
    }

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

        // 1차 카테고리 불러오기(자주묻는 질문,문의하기 일시)
        if(cate.equals("faq") || cate.equals("qna")) {
            // 기본 적으로 카테고리1 리스트 불러옴
            List<Cs_Cate1VO> cate1VOs = selectCsCate1s();
            data.put("cate1VOs", cate1VOs);
    
            // 카테고리1값이 들어오며 카테고리2값도 있을 경우 => 카테고리2 리스트 불러옴
            if(!param.getCate1().equals("") && !param.getCate2().equals("")) {
                List<Cs_Cate2VO> cate2VOs = selectCsCate2sWithCate1(param.getCate1());
                data.put("cate2VOs", cate2VOs);
            }
        }
        
        // 페이징 처리
        PagingDTO paging = new PagingUtil().getPagingDTO(param.getPg(), selectCountCsArticle(cate, param));
        data.put("paging", paging);

        // 카테고리에 따른 목록 불러서 map에 넣기
        List<AdminCsVo> articles = selectCsArticles(cate, paging.getStart(), param);
        data.put("articles", articles);
        
        // 파라미터 map 입력
        data.put("etcText", param);

        return data;
    }

    /**
     * 2023/02/15 // 심규영 // 관리자 고객센터 리스트 종합 처리 param null 처리
     * @param param
     */
    public void paramProcess(AdminCsListParamDTO param) {
        if(param.getPg() == null) param.setPg("");
        if(param.getType() == null) param.setType("");
        if(param.getCate1() == null) param.setCate1("");
        if(param.getCate2() == null) param.setCate2("");
    }

}
