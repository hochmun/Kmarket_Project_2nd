package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class member_termsVO {
    private String terms;
    private String privacy;
    private String location;
    private String finance;
    private String tax;
}
