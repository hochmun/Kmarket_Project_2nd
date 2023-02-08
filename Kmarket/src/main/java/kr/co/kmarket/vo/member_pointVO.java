package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class member_pointVO {
    private int pointNO;
    private String uid;
    private int ordNo;
    private int point;
    private Date pointDate;
}
