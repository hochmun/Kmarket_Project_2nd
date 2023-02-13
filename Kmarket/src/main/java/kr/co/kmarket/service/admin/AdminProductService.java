package kr.co.kmarket.service.admin;

import kr.co.kmarket.dao.admin.AdminProductDAO;
import kr.co.kmarket.vo.productVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * 관리자 상품 서비스 <br>
 * @since 2023/02/09 // 심규영 // 관리자 상품 서비스 생성
 */
@Slf4j
@Service
public class AdminProductService {

    /** 
     * 2023/02/13 // 심규영 // 파일 업로드 경로
     */
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;
    
    /**
     * @since 2023/02/09 // 심규영 // 관리자 상품 DAO 연결
     */
    @Autowired
    private AdminProductDAO dao;

    // create

    /**
     * <br>2023/02/13 // 심규영 // 관리자 상품 등록 서비스 기능
     * @param vo
     */
    public int insertProduct(productVO vo){
        // 이미지 이름 변경 후 등록
        vo.setThumb1(fileUpload(vo.getThumb1File(), vo.getProdCate1(), vo.getProdCate2()));
        vo.setThumb2(fileUpload(vo.getThumb2File(), vo.getProdCate1(), vo.getProdCate2()));
        vo.setThumb3(fileUpload(vo.getThumb3File(), vo.getProdCate1(), vo.getProdCate2()));
        vo.setDetail(fileUpload(vo.getDetailFile(), vo.getProdCate1(), vo.getProdCate2()));

        // 포인트 계산
        vo.setPoint((int)(vo.getPrice() * (1 - (vo.getDiscount() / 100.0))) / 100);

        return dao.insertProduct(vo);
    }

    // read

    /**
     * 상품 삭제시 삭제할 이미지 정보 불러오는데 사용중
     * 2023/02/13 // 심규영 // 관리자 상품 불러오기
     * @param prodNo
     * @return
     */
    public productVO selectProduct(String prodNo){
        return dao.selectProduct(prodNo);
    }

    /**
     * 관리자 상품 리스트 불러오기<br>
     * 유저 아이디에 마스터피스 추가
     * 검색기능 추가
     * @since 2023/02/09 // 심규영 // 관리자 상품 리스트 불러오는 메소드 작성<br>
     *        2023/02/10 // 심규영 // 관리자 상품 리스트 검색 기능 추가
     * @param uid
     * @param start
     * @return
     */
    public List<productVO> selectProducts(String uid, int start, String s, String st){
        if(uid.equals("")) uid = "%"+uid+"%";
        if(st.equals("")) st = "prodNo";
        s = "%"+s+"%";
        return dao.selectProducts(uid, start, s, st);
    }

    /**
     * 관리자 상품 리스트 갯수 구하기
     * @since 2023/02/09 // 심규영 // 관리자 상품 리스트 갯수 구하기 작성<br>
     * 2023/02/10 // 심규영 // 검색 기능 추가
     * @param uid
     * @return 상품 갯수
     */
    public int selectCountProduct(String uid, String s, String st) {
        if(st.equals("")) st = "prodNo";
        s = "%"+s+"%";
        return dao.selectCountProduct(uid, s, st);
    }
    // upload

    // delete

    /**
     * 관리자 상품 삭제 기능<br>
     * 판매자 회원일 경우 자신의 상품만 삭제 가능<br>
     * 관리자 회원일 경우 전체 상품 삭제 가능<br>
     * 데이터 베이스에서 상품 삭제 전 서버에서 이미지 파일 제거
     * @since 2023/02/13 // 심규영 // 최초작성
     * @param prodNo
     * @param uid
     * @return
     */
    public int deleteProduct(String prodNo, String uid, int type) {
        // 관리자 일 경우 전체 상품에 영향 줄 수 있음
        if(type == 5) uid = "%%"; 
        
        // 데이터 베이스에서 상품 정보 제거전 서버에서 파일 삭제
        deleteFile(prodNo);
        
        // 데이터 베이스에 상품 정보 삭제
        return dao.deleteProduct(prodNo, uid);
    }

    // service

    /**
     * <br>2023/02/13 // 심규영 // 파일 업로드 기능 서비스
     * @param file
     */
    public String fileUpload(MultipartFile file, int cate1, int cate2) {
        // 시스템 경로
        String path = new File(uploadPath).getAbsolutePath();

        // 이름 변경
        String oName = file.getOriginalFilename();
        String ext = oName.substring(oName.lastIndexOf("."));
        String nName = UUID.randomUUID().toString()+ext;
        
        // 파일 저장
        try {
            file.transferTo(new File(String.format("%s/%d/%d/", path, cate1, cate2), nName));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return nName;
    }

    /**
     * 관리자 상품 삭제전 서버에 이미지 제거 기능
     * @since 2023/02/13 // 심규영 // 최초작성
     * @param prodNo
     */
    public void deleteFile(String prodNo) {
        // 삭제할 상품 이미지 정보 불러오기
        productVO product = selectProduct(prodNo);
        int cate1 = product.getProdCate1();
        int cate2 = product.getProdCate2();

        // 파일 경로 설정
        String path = new File(uploadPath).getAbsolutePath();
        
        File thumb1 = new File(String.format("%s/%d/%d/%s", path, cate1, cate2, product.getThumb1()));
        File thumb2 = new File(String.format("%s/%d/%d/%s", path, cate1, cate2, product.getThumb2()));
        File thumb3 = new File(String.format("%s/%d/%d/%s", path, cate1, cate2, product.getThumb3()));
        File detail = new File(String.format("%s/%d/%d/%s", path, cate1, cate2, product.getDetail()));

        // 해당 경로에 해당하는 이미지파일이 있을경우 삭제
        if(thumb1.exists()) thumb1.delete();
        if(thumb2.exists()) thumb2.delete();
        if(thumb3.exists()) thumb3.delete();
        if(detail.exists()) detail.delete();
    }
}
