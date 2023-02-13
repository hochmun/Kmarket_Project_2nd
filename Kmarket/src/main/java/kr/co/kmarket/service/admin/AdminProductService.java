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
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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
    /**
     * 관리자 상품 삭제 상태로 변경 기능
     * <br>다중 처리 기능 추가
     * @since 2023/02/13 // 심규영 // 최초작성
     */
    public int updateProductDeleteStatus(String[] arrays, String uid, int type){
        // 관리자이면 전체 상품 삭제 허용
        if(type == 5) uid = "%%";

        int result = 0;

        for(String prodNo : arrays){
            result += dao.updateProductDeleteStatus(prodNo, uid);
        }

        return result;
    }

    /**
     * 관리자 상품 수정 기능
     * @since 2023/02/13 // 심규영 // 최초작성
     * @return
     */
    public int updateProduct(HashMap<String, Object> map, String uid){
        String prodName = (String) map.get("prodName");
        String price = (String) map.get("price");
        String discount = (String) map.get("discount");
        String stock = (String) map.get("stock");
        String prodNo = (String) map.get("prodNo");

        return dao.updateProduct(prodName, price, discount, stock, prodNo, uid);
    }

    // delete


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

}
