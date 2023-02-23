package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 2023/02/23 // 심규영 // 상품 문의 VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product_qnaVO {
    private int pqnaNo;
    private int pqnaCate;
    private int pqnaType;
    private String pqnaEnUid;
    private String pqnaReUid;
    private String pqnaEmail;
    private String pqnaTitle;
    private String pqnaContent;
    private String pqnaReContent;
    private String pqnaRdate;
    private String pqnaRegip;

}
