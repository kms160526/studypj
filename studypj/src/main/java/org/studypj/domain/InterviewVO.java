package org.studypj.domain;

import lombok.Data;

import java.util.Date;

@Data
public class InterviewVO {

    private int interview_no;
    private String interview_name;
    private String interview_date;
    private String interview_address;
    private int interview_progress;

    private Date interview_regdate;

}
