package kr.co.kmarket.vo;
/*
 *  날짜 : 2023/02/08
 *  이름 : 이해빈
 *  내용 : 상품 cate2 VO
 *     - 2022/02/09 c1Name 추가
 * */

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class product_cate2VO {

    private int cate1;
    private int cate2;
    private String c1Name;
    private String c2Name;


}
