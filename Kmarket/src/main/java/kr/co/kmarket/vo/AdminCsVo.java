package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 2023/02/15 // 심규영 // 관리자 고객센터 VO 합치기
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminCsVo {
    private int faqNo;
    private int faqCate1;
    private int faqCate2;
    private String faqTitle;
    private String faqContent;
    private String faqRegip;
    private int faqHit;
    private String faqRdate;


    private int noticeNo;
    private int noticeCate;
    private String noticeTitle;
    private String noticeContent;
    private String noticeRdate;
    private String noticeRegip;
    private int noticeHit;


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

    // 추가 필드
    private String c1Name; // 카테고리1 이름
    private String c2Name; // 카테고리2 이름
    private String qnaName; // 작성자 이름

    // 출력

    /**
     * 2023/02/15 // 심규영 // 자주묻는 질문 날짜 출력
     * @return
     */
    public String getFaqRdate() {
        return faqRdate.substring(0, 10);
    }

    /**
     * 2023/02/15 // 심규영 // 공지사항 날짜 출력
     * @return
     */
    public String getNoticeRdate() {
        return noticeRdate.substring(0, 10);
    }

    /**
     * 2023/02/15 // 심규영 // 문의하기 날짜 출력
     * @return
     */
    public String getQnaRdate() {
        return qnaRdate.substring(0, 10);
    }
}
