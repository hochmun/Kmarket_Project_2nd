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
    private String content;
    private String uid;
    private int rating;
    private String varchar;
    private String rdate;
}
