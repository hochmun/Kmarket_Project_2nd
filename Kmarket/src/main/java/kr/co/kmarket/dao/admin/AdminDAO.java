package kr.co.kmarket.dao.admin;

import kr.co.kmarket.dto.AdminMainStatusDTO;
import kr.co.kmarket.vo.Cs_NoticeVO;
import kr.co.kmarket.vo.Cs_QnaVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 2023/02/17 // 심규영 // 관리자 메인 DAO
 */
@Mapper
@Repository
public interface AdminDAO {
    // create
    // read

    /**
     * 2023/02/20 // 심규영 // 관리자 메인 운영현황 불러오기
     * 쇼핑몰 방문자 제외
     * @return
     */
    public AdminMainStatusDTO selectAdminMainStatus();

    /**
     * 2023/02/20 // 심규영 // 관리자 메인 공지사항 날짜 내림차순 5개 가져오기
     */
    public List<Cs_NoticeVO> selectNoticeArticle5();

    /**
     * 2023/02/20 // 심규영 // 관리자 메인 문의사항 날짜 내림차순 5개 가져오기
     */
    public List<Cs_QnaVO> selectQnaArticle5();
    // upload
    // delete
}
