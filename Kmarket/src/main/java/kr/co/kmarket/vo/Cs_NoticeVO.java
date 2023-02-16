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
public class Cs_NoticeVO {
    private int noticeNo;
    private int noticeCate;
    private String noticeTitle;
    private String noticeContent;
    private String noticeRdate;
    private String noticeRegip;
    private int noticeHit;

    // noticeCate int -> String
    public int getNoticeCate() {
        return noticeCate;
    }
    public void setNoticeCate(String noticeCate) {
        this.noticeCate = Integer.parseInt(noticeCate);
    }

    // 작성일 자르기
    public String getNoticeRdate(){
        return noticeRdate.substring(2, 10);
    }
}
