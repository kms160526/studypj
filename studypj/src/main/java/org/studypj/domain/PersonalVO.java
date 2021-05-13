package org.studypj.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PersonalVO {

    private int personal_no;
    private String name_ko;
    private String name_en;
    private String name_cn;
    private String birth;
    private String address;
    private String contact;
    private String phone;
    private String email;

    private Date regdate;

}
