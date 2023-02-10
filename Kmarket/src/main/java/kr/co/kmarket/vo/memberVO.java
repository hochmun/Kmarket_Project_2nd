package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class memberVO {

    private String uid;
    private String pass;
    private String pass1;
    private String pass2;
    private String name;
    private int gender;
    private String hp;
    private String email;
    private int type;
    private int point;
    private int level;
    private String zip;
    private String addr1;
    private String addr2;
    private String company;
    private String ceo;
    private String bizRegNum;
    private String comRegNum;
    private String tel;
    private String manager;
    private String managerHp;
    private String fax;
    private String regip;
    private String wdate;
    private String rdate;
    private int etc1;
    private int etc2;
    private String etc3;
    private String etc4;
    private String etc5;

    // (추가) Gender : int -> String
    public void setGender(String gender) {
        this.gender = Integer.parseInt(gender);
    }
}
