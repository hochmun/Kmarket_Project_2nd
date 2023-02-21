package kr.co.kmarket.vo;

import lombok.*;

/*
 *  날짜 : 2023/02/21
 *  이름 : 이해빈
 *  내용 : TermsPolicy VO
 * */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TermsPolicyVO {

    private int type;
    private String title;
    private int titleNum;
    private String subTitle;
    private String subTitleNum;
    private String content;

}
