package org.studypj.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ResumeVO {

    private int resume_no;
    private int personal_statement_no;
    private int education_group_no;
    private int personal_no;
    private int training_group_no;

    private Date regdate;

}
