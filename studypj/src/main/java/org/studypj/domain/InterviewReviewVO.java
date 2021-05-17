package org.studypj.domain;

import lombok.Data;

import java.util.Date;

@Data
public class InterviewReviewVO {

    private int interview_review_no;
    private int interview_no;
    private String interview_review_content;

    private Date regdate;
}
