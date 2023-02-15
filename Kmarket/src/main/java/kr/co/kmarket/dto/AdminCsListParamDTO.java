package kr.co.kmarket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 2023/02/14 // 심규영 // 관리자 고객센터 리스트 데이터 커맨드
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminCsListParamDTO {
    private String cate1;
    private String cate2;
    private String type;
    private String pg;

}
