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

        // interview의 progress를 체크, progress가 2면 false
        if(!interviewProgressCheck(interview_no)){
            return false;
        }

        return interviewMapper.delete(interview_no) == 1;
    }

    @Override
    public boolean modify(InterviewVO interview) {

        log.info("interview modify........ ");

        // interview의 progress를 체크, progress가 2면 false
        if(!interviewProgressCheck(interview.getInterview_no())){
            return false;
        }

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

    // interview의 progress를 체크하여 면접본 곳의 정보를 수정하려고하면 false를 return
    public boolean interviewProgressCheck(int interview_no){

        InterviewVO checkVO =  interviewMapper.read(interview_no);

        if(checkVO.getInterview_progress() == 2){
            return false;
        }

        return true;
    }
}
