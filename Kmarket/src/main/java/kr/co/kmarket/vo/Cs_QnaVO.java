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
public class Cs_QnaVO {
    private int qnaNo;
    private int qnaCate1;
    private int qnaCate2;
    private String uid;
    private String qnaTitle;
    private String qnaContent;
    private String qnaAdminContent;
    private int qnaType;
    private String qnaRdate;
    private String qnaRegip;

    // 추가필드
    private int cate1;
    private String cate1Name;
    private int cate2;
    private String cate2Name;

    // 작성일 자르기
    public String getQnaRdate(){
        return qnaRdate.substring(2, 10);
    }

    // 추가 get, set
    // qnaNo int -> String 변환
    public int getQnaNo() {
        return qnaNo;
    }
    public void setQnaNo(String qnaNo) {
        this.qnaNo = Integer.parseInt(qnaNo);
    }

    // qnaCate1 int -> String 변환
    public int getQnaCate1() {
        return qnaCate1;
    }
    public void setQnaCate1(String qnaCate1) {
        this.qnaCate1 = Integer.parseInt(qnaCate1);
    }

    // qnaCate2 int -> String 변환
    public int getQnaCate2() {
        return qnaCate2;
    }
    public void setQnaCate2(String qnaCate2) {
        this.qnaCate2 = Integer.parseInt(qnaCate2);
    }
}
