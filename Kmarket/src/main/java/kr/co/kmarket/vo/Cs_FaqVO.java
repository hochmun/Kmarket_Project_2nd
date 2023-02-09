package kr.co.kmarket.vo;

import lombok.*;

/**
 * 날짜 : 23/02/08
 * 이름 : 김재준
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Cs_FaqVO {
    private int faqNo;
    private int faqCate1;
    private int faqCate2;
    private String faqTItle;
    private String faqContent;
    private String faqRegip;
    private int faqHit;
    private String faqRdate;
}
