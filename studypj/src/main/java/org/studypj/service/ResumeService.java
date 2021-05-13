package org.studypj.service;


import org.studypj.domain.Criteria;
import org.studypj.domain.PersonalStatementVO;
import org.studypj.domain.ResumeVO;

import java.util.List;

public interface ResumeService {

    public boolean register(ResumeVO resume);

    public boolean remove(int resume_no);

//    public boolean modify(ResumeVO resume);

    public ResumeVO get(int resume_no);

    //    페이징 처리 추가
    public List<ResumeVO> getList(Criteria cri);

    //     페이징 처리 관련 getTotal
    public int getTotal();


}
