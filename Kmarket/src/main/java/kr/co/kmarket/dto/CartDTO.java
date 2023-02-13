package kr.co.kmarket.dto;

import lombok.*;

/**
 * 날짜 : 2023/02/12
 * 이름 : 이해빈
 * 내용 : 장바구니 데이터 전송 DTO
 * */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CartDTO {

    private int cartNo;
    private String uid;
    private int prodNo;
    private int count;
    private int price;
    private int discount;
    private int point;
    private int delivery;
    private int total;
    private String rdate;

    // 추가필드
    private String type;
    private String prodName;
    private int prodCate1;
    private int prodCate2;
    private String thumb1;
    private String descript;

}

