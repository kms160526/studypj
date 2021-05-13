package org.studypj.domain;

import lombok.Data;

import java.util.Date;

@Data
public class EducationVO {

    private int education_group_no;
    private int education_no;
    private String education_term;
    private String school_name;
    private int grated;

    private Date regdate;
}
