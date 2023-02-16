package kr.co.kmarket.vo;
/*
 *  날짜 : 2023/02/08
 *  이름 : 이해빈
 *  내용 : 상품 order item VO
 *      - 2023/02/15 추가필드
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

    // 추가 필드
    private String prodName;
    private int prodCate1;
    private int prodCate2;
    private String thumb1;
    private String descript;
}
