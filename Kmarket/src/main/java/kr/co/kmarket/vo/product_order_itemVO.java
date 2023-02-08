package kr.co.kmarket.vo;
/*
 *  날짜 : 2023/02/08
 *  이름 : 이해빈
 *  내용 : 상품 order item VO
 * */

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class product_order_itemVO {

    private int ordNo;
    private int prodNo;
    private int count;
    private int price;
    private int discount;
    private int point;
    private int delivery;
    private int total;
}
