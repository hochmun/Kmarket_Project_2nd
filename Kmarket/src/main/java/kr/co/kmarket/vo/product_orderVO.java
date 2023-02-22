package kr.co.kmarket.vo;
/*
 *  날짜 : 2023/02/08
 *  이름 : 이해빈
 *  내용 : 상품 order VO
 *      - 2023/02/08 추가필드
 * */

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class product_orderVO {
    private int ordNo;
    private String ordUid;
    private int ordCount;
    private int ordPrice;
    private int ordDiscount;
    private int ordDelivery;
    private int savePoint;
    private int usedPoint;
    private int ordTotPrice;
    private String recipName;
    private String recipHp;
    private String recipZip;
    private String recipAddr1;
    private String recipAddr2;
    private int ordPayment;
    private int ordComplete;
    private String ordDate;


    // 추가필드
    private String name;
    private String hp;
    private String prodNo;
    private int countPrice; // 갯수 * 가격
    private int disPrice; // (갯수 * 가격) * (discount / 100) // 할인되는 가격

    // 추가 필드 km_product
    private int prodCate1;
    private int prodCate2;
    private String thumb1;
    private String company;
    private String prodName;
    private String uid;
    private int price;

    // 추가 필드 km_product_order_item
    private int count;
    private int total;

}
