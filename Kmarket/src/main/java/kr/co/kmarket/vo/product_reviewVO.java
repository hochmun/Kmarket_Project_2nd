package kr.co.kmarket.vo;
/*
 *  날짜 : 2023/02/08
 *  이름 : 이해빈
 *  내용 : 상품 reivew VO
 * */

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class product_reviewVO {
    private int revNo;
    private int prodNo;
    private int ordNo;
    private String content;
    private String uid;
    private int rating;
    private String regip;
    private String rdate;

    // 추가필드
    // my_review에 불러올 상품명, 카테고리 // 23/02/21 김재준
    private String prodName;
    private int prodCate1;
    private int prodCate2;
}
