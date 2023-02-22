package kr.co.kmarket.dto;

import lombok.*;

/**
 * 날짜 : 2023/02/22
 * 이름 : 이해빈
 * 내용 : 상품 검색 DTO
 * */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SearchDTO {

    private String keyword; // 1차 검색 키워드
    private String keywords; // 2차 검색 키워드
    private boolean chk1; // 상품명으로 검색여부
    private boolean chk2; // 상품설명으로 검색여부
    private boolean chk3; // 가격조건으로 검색여부
    private String min; // 최소가격조건
    private String max; // 최대가격조건

}
