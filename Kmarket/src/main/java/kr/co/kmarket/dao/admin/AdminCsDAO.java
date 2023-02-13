package kr.co.kmarket.dao.admin;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

    // upload
    public void uploadCsArticle();

    // delete
    public void deleteCsArticle();

}
