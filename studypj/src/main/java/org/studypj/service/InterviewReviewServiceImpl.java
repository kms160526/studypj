package org.studypj.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studypj.domain.InterviewReviewVO;
import org.studypj.mapper.InterviewReviewMapper;

@Service
@Log4j
public class InterviewReviewServiceImpl implements InterviewReviewService{

    @Setter(onMethod_ = @Autowired)
    private InterviewReviewMapper interviewReviewMapper;

    @Transactional
    @Override
    public boolean register(InterviewReviewVO interviewReview) {

        log.info("interviewReview register...........");

        // 만약 해당 면접처에 대한 후기가 이미 존재한다면 false를 return, 서버단에서 추가처리
        InterviewReviewVO checkVo = interviewReviewMapper.read(interviewReview.getInterview_no());
        if(checkVo != null){
            log.info("Review already exists");
            return false;
        }

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
    public boolean remove(int interview_review_no) {

        log.info("interviewReview remove ....... targetNo -> " + interview_review_no);

        return interviewReviewMapper.delete(interview_review_no) == 1;
    }

    @Override
    public int getTotal() {

        log.info("getTotal interviewReview .......");

        return interviewReviewMapper.getTotalCount();
    }
}
