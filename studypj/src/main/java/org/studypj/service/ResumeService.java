package org.studypj.service;


import org.studypj.domain.*;

import java.util.List;

public interface ResumeService {

    public boolean register(ResumeVO resume);

    public boolean remove(int resume_no);

    // resume를 update 한다. 추가로 개인신상, 학력, 교육연수의 항목들도 같이 update 된다.
    // 트랜잭션 처리를 한다.
    public boolean modify(ResumeVO resume, PersonalVO personal,
                          EducationVO education, TrainingVO training);

    public ResumeVO get(int resume_no);

    //    페이징 처리 추가
    public List<ResumeVO> getList(Criteria cri);

    //     페이징 처리 관련 getTotal
    public int getTotal();


}
