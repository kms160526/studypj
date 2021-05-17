package org.studypj.mapper;

import org.studypj.domain.Criteria;
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
    // total
    public int getTotalCount();

    // getListWithPaging
    // getListWithPaging PersonalStatement
    // getList 페이징 처리
    public List<InterviewVO> getListWithPaging(Criteria cri);


}
