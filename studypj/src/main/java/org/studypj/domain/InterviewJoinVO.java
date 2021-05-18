package org.studypj.domain;

import lombok.Data;

import java.util.Date;

@Data
public class InterviewJoinVO {
    // interview 관련
    private int interview_no;
    private String interview_name;
    private String interview_date;
    private String interview_address;
    private int interview_progress;

    private Date interview_regdate;

    // interviewReview 관련
    private int interview_review_no;
    private String interview_review_content;

    private Date regdate;

}
