package org.studypj.service;

import org.studypj.domain.InterviewReviewVO;

public interface InterviewReviewService {

    // insert - interviewReview, register
    public boolean register(InterviewReviewVO interviewReview);

    // read - interviewReview, get
    public InterviewReviewVO get(int interview_no);

    // update - interviewReview, modify
    public boolean modify(InterviewReviewVO interviewReview);

    // 페이징 처리 관련 getTotal
    public int getTotal();
}
