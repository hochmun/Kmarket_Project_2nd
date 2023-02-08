package kr.co.kmarket.vo;

import lombok.*;

/*
 *  날짜 : 2023/02/08
 *  이름 : 이해빈
 *  내용 : 상품 cart VO
 * */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class product_cartVO {

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
