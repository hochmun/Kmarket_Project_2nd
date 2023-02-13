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
    private String cate1Name;
    private String cate2Name;


    // faqNo int -> String 변환
    public int getFaqNo() {
        return faqNo;
    }
    public void setFaqNo(String faqNo) {
        this.faqNo = Integer.parseInt(faqNo);
    }

    // faqCate1 int -> String 변환
    public int getFaqCate1() {
        return faqCate1;
    }
    public void setFaqCate1(String faqCate1) {
        this.faqCate1 = Integer.parseInt(faqCate1);
    }

    // faqCate2 int -> String 변환
    public int getFaqCate2() {
        return faqCate2;
    }
    public void setFaqCate2(String faqCate2) {
        this.faqCate2 = Integer.parseInt(faqCate2);
    }

}
