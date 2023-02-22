package kr.co.kmarket.vo;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SearchVO {


    private String keyword; // 1차 검색 키워드
    private String keywords; // 2차 검색 키워드
    private boolean chk1; // 상품명으로 검색여부
    private boolean chk2; // 상품설명으로 검색여부
    private boolean chk3; // 가격조건으로 검색여부
    private int min; // 최소가격조건
    private int max; // 최대가격조건

}
