package org.studypj.service;

import org.studypj.domain.Criteria;
import org.studypj.domain.InterviewJoinVO;
import org.studypj.domain.InterviewVO;

import java.util.List;

public interface InterviewService {

    public boolean register(InterviewVO interview);

    public boolean remove(int interview_no);

    public boolean modify(InterviewVO interview);

    public InterviewVO get(int interview_no);

    // Intreview에서 사용할 페이징 처리
    public List<InterviewVO> getList(Criteria cri);

    // intreviewReview에서 사용할 페이징 처리
    public List<InterviewJoinVO> getListWithProgress(Criteria cri);

    //     페이징 처리 관련 getTotal
    public int getTotal();

}
