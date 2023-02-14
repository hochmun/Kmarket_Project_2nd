package kr.co.kmarket.dto;

import lombok.*;

/**
 * 날짜 : 23/02/14
 * 이름 : 김재준
 * 내용 : 카테고리2 데이터 전송 DTO
 */
@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cate2DTO {
    private int cate1;
    private int cate2;
    private String cate2Name;
}
