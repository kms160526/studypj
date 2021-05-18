package org.studypj.mapper;

import org.studypj.domain.InterviewReviewVO;

public interface InterviewReviewMapper {
    // insert InterviewReview
    public int insert(InterviewReviewVO interviewReview);

    // update InterviewReview
    public int update(InterviewReviewVO interviewReview);

    // delete InterviewReview
    public int delete(int interview_review_no);

    // read - InterviewReview
    public InterviewReviewVO read(int interview_no);

    // 페이징 처리 관련 - interviewReview의 갯수를 체크
    public int getTotalCount();
}
