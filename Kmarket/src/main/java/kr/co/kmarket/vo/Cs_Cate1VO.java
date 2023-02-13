package kr.co.kmarket.vo;

import lombok.*;

/**
 * 날짜 : 23/02/08
 * 이름 : 김재준
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Cs_Cate1VO {
    private int cate1;
    private String cate1Name;

    // 추가필드
    private int cate2;
    private String cate2Name;
}
