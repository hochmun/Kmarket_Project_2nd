package kr.co.kmarket.vo;

import lombok.*;

/**
 * 날짜 : 23/02/08
 * 이름 : 김재준
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cs_FaqVO {
    private int faqNo;
    private int faqCate1;
    private int faqCate2;
    private String faqTitle;
    private String faqContent;
    private String faqRegip;
    private int faqHit;
    private String faqRdate;

    // 추가필드
    private int cate1;
    private int cate2;

}
