package kr.co.kmarket.dto;

import lombok.Data;

/**
 * 2023/02/20 // 심규영 // 관리자 메인 상태 객체를 담는 Data Transfer Object
 */
@Data
public class AdminMainStatusDTO {
    private int countOrdNo;
    private int countOrdNoDay;
    private int countOrdNoWeek;
    private int countOrdNoMonth;
    private int sumOrdTotPrice;
    private int sumOrdTotPriceDay;
    private int sumOrdTotPriceWeek;
    private int sumOrdTotPriceMonth;
    private int countMember;
    private int countMemberDay;
    private int countMemberWeek;
    private int countMemberMonth;
    private int countProductRdateDay;
    private int countProductRdateWeek;
    private int countProductRdateMonth;
}
