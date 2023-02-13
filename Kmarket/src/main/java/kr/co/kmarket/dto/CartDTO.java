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

}

