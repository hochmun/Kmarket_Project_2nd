package kr.co.kmarket.dao.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @since 2023/02/13 // 심규영 // 관리자 고객센터 DAO 생성
 */
@Mapper
@Repository
public interface AdminCsDAO {
    // create
    public void createCsArticle();

    // read
    public void selectCsArticle();
    public void selectCsArticles();

    /**
     * 2023/02/14 // 심규영 // 게시물 총 갯수 계산, 최초 접속시 계산
     * @param map
     *      noName = 게시물이름 + No
     *      table = 불러올 테이블 이름
     *      cate = 게시물 종류
     *      type = notice에서 카테고리
     *      cate1 = csCate1 값
     *      cate2 = csCate2 값
     */
    public int selectCountCsArticle(Map<String, String> map);

    // upload
    public void uploadCsArticle();

    // delete
    public void deleteCsArticle();

}
