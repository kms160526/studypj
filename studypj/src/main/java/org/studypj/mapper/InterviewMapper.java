package org.studypj.mapper;

import org.studypj.domain.Criteria;
import org.studypj.domain.InterviewJoinVO;
import org.studypj.domain.InterviewVO;
import org.studypj.domain.PersonalStatementVO;

import java.util.List;

public interface InterviewMapper {

    // insert Interview
    public int insert(InterviewVO interview);

    // delete Interview
    public int delete(int interview_no);

    // update Interview
    public int update(InterviewVO interview);

    // read - Interview
    public InterviewVO read(int interview_no);

    // 페이징 처리 관련
    public int getTotalCount();

    // getListWithPaging
    // getList 페이징 처리
    public List<InterviewVO> getListWithPaging(Criteria cri);

    // getListWithProgress IntreviewReview 화면에서 리스트를 출력하기 위함
    // intreview_progress == 2 인 것들만 리스트로 가져온다.
    public List<InterviewJoinVO> getListWithPagingProgress(Criteria cri);

}
