package kr.co.kmarket.vo;
/*
*  날짜 : 2023/02/08
*  이름 : 이해빈
*  내용 : 상품 VO
* */

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class productVO {

    private int prodNo;
    private int prodCate1;
    private int prodCate2;
    private String prodName;
    private String descript;
    private String company;
    private String seller;
    private int price;
    private int discount;
    private int point;
    private int stock;
    private int sold;
    private int delivery;
    private int hit;
    private int score;
    private int review;
    private String thumb1;
    private String thumb2;
    private String thumb3;
    private String detail;
    private String status;
    private String duty;
    private String receipt;
    private String bizType;
    private String origin;
    private String ip;
    private String rdate;
    private int prodStatus;
    private int etc2;
    private String etc3;
    private String uid;
    private String etc5;
    
    // 추가 필드
    private int disPrice; // 할인율이 적용된 값

    private MultipartFile thumb1File;
    private MultipartFile thumb2File;
    private MultipartFile thumb3File;
    private MultipartFile detailFile;
}
