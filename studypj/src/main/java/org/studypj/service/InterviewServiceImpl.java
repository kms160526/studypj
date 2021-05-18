package org.studypj.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studypj.domain.Criteria;
import org.studypj.domain.InterviewJoinVO;
import org.studypj.domain.InterviewVO;
import org.studypj.mapper.InterviewMapper;

import java.util.List;

@Service
@Log4j
public class InterviewServiceImpl implements InterviewService{

    @Setter(onMethod_ = @Autowired)
    private InterviewMapper interviewMapper;

    @Override
    public boolean register(InterviewVO interview) {

        log.info("interview register............... ");

        return interviewMapper.insert(interview) == 1;
    }

    @Override
    public boolean remove(int interview_no) {

        log.info("interview Remove....... targetNo : " + interview_no);

        return interviewMapper.delete(interview_no) == 1;
    }

    @Override
    public boolean modify(InterviewVO interview) {

        log.info("interview modify........ ");

        return interviewMapper.update(interview) == 1;
    }

    @Override
    public InterviewVO get(int interview_no) {

        log.info("interview Get ....... targetNo : " + interview_no);

        return interviewMapper.read(interview_no);
    }

    @Override
    public List<InterviewVO> getList(Criteria cri) {

        log.info("interview getList...... ");

        return interviewMapper.getListWithPaging(cri);
    }

    @Override
    public List<InterviewJoinVO> getListWithProgress(Criteria cri) {

        log.info("interview getList With Progress 2 ");

        return interviewMapper.getListWithPagingProgress(cri);
    }

    @Override
    public int getTotal() {

        log.info("interview getTotal ........... ");

        return interviewMapper.getTotalCount();
    }
}
