package kr.co.kmarket.vo;

import lombok.*;

/**
 * 2023/02/23
 * 배너 VO
 * @author 이해빈
 * */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BannerVO {

    private int bannerNo;
    private String bannerName;
    private String bannerSize;
    private String bannerColor;
    private String bannerLink;
    private String bannerPosition;
    private String bannerSdate;
    private String bannerEdate;
    private String bannerStime;
    private String bannerEtime;
    private int bannerHit;
    private int bannerStatus;

}
