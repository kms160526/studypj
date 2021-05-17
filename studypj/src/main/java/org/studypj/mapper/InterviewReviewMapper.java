package org.studypj.mapper;

import org.studypj.domain.InterviewReviewVO;

public interface InterviewReviewMapper {
    // insert Interview
    public int insert(InterviewReviewVO interviewReview);

    // delete Interview
    public int delete(int interview_review_no);

    // update Interview
    public int update(InterviewReviewVO interviewReview);

    // read - Interview
    public InterviewReviewVO read(int interview_review_no);
}
