package org.studypj.mapper;

import org.studypj.domain.InterviewReviewVO;

public interface InterviewReviewMapper {
    // insert Interview
    public int insert(InterviewReviewVO interviewReview);

    // update Interview
    public int update(InterviewReviewVO interviewReview);

    // read - Interview
    public InterviewReviewVO read(int interview_no);

    // 페이징 처리 관련 - interviewReview의 갯수를 체크
    public int getTotalCount();
}
