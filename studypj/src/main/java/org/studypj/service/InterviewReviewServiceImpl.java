package org.studypj.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studypj.domain.InterviewReviewVO;
import org.studypj.mapper.InterviewReviewMapper;

@Service
@Log4j
public class InterviewReviewServiceImpl implements InterviewReviewService{

    @Setter(onMethod_ = @Autowired)
    private InterviewReviewMapper interviewReviewMapper;

    @Override
    public boolean register(InterviewReviewVO interviewReview) {

        log.info("interviewReview register...........");

        return interviewReviewMapper.insert(interviewReview) == 1;
    }

    @Override
    public InterviewReviewVO get(int interview_no) {

        log.info("interviewReview get ...... targetNo : " + interview_no);

        return interviewReviewMapper.read(interview_no);
    }

    @Override
    public boolean modify(InterviewReviewVO interviewReview) {

        log.info("interviewReview modify ....... ");

        return interviewReviewMapper.update(interviewReview) == 1;
    }

    @Override
    public int getTotal() {

        log.info("getTotal interviewReview .......");

        return interviewReviewMapper.getTotalCount();
    }
}
