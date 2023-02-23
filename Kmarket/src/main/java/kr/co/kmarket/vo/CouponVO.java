package kr.co.kmarket.vo;

import lombok.*;

import java.util.Date;

/**
 * 2023/02/23
 * 쿠폰 VO
 * @author 이해빈
 * */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CouponVO {

    private int couponId;
    private String couponName;
    private String uid;
    private int couponStatus;
    private int couponDisprice;
    private int couponUsePriceCondition;
    private String couponCdate;
    private String couponEdate;
    private String couponUdate;

}
