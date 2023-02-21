package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    
    // 출력 포맷

    /**
     * 2023/02/21 // 심규영 // 적립 날짜 출력
     * @return
     */
    public String getPointDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(pointDate);
    }

    public String getLastDateString() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        cal.setTime(pointDate);
        cal.add(Calendar.YEAR, 2);

        return simpleDateFormat.format(cal.getTime());
    }

}
