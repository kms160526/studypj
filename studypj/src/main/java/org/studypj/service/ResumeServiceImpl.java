package org.studypj.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studypj.domain.*;
import org.studypj.mapper.EducationMapper;
import org.studypj.mapper.PersonalMapper;
import org.studypj.mapper.ResumeMapper;
import org.studypj.mapper.TrainingMapper;

import java.util.List;

@Service
@Log4j
public class ResumeServiceImpl implements ResumeService{

    @Setter(onMethod_ = @Autowired)
    private ResumeMapper resumeMapper;

    @Setter(onMethod_ = @Autowired)
    private PersonalMapper personalMapper;

    @Setter(onMethod_ = @Autowired)
    private EducationMapper educationMapper;

    @Setter(onMethod_ = @Autowired)
    private TrainingMapper trainingMapper;


    @Override
    public boolean register(ResumeVO resume) {

        log.info("resume register...........service");

        boolean registerResult = resumeMapper.insert(resume) == 1;

        return registerResult;
    }

    @Override
    public boolean remove(int resume_no) {

        log.info("resume remove...........service");

        boolean registerResult = resumeMapper.delete(resume_no) == 1;

        return registerResult;
    }


    // resume를 update 한다. 추가로 개인신상, 학력, 교육연수의 항목들도 같이 update 된다.
    // 트랜잭션 처리를 한다.
    @Transactional
    @Override
    public boolean modify(ResumeVO resume, PersonalVO personal,
                          EducationVO education, TrainingVO training) {

        // 문제가 생겼을 경우 어디서 문제가 생겼는지 알기위해서 하나씩 체크한다.
        boolean modifyResult = false;
        boolean modifyResumeResult = false;
        boolean modifyPersonalResult = false;
        boolean modifyEducationResult = false;
        boolean modifyTrainingResult = false;

        log.info("resume modify...........service");

        // 개인신상정보 update
        modifyPersonalResult = personalMapper.update(personal) == 1;
        log.info("modify Personal Result -> " + modifyPersonalResult);

        // 교육정보 update
        modifyEducationResult = educationMapper.update(education) == 1;
        log.info("modify Education Result -> " + modifyEducationResult);

        // 교육연수정보 update
        modifyTrainingResult = trainingMapper.update(training) == 1;
        log.info("modify Training Result -> " + modifyTrainingResult);

        // resume update - personal_statement_no 의 수정이 있을 수 있다.
        modifyResumeResult = resumeMapper.update(resume) == 1;
        log.info("modify Resume Result -> " + modifyResumeResult);

        modifyResult = modifyPersonalResult && modifyEducationResult && modifyTrainingResult && modifyResumeResult;
        log.info("최종 modify Resume Result -> " + modifyResult);

        return modifyResult;
    }

    @Override
    public ResumeVO get(int resume_no) {

        log.info("resume get...........service");

        return resumeMapper.read(resume_no);
    }

    // getList -> getListWithPaging
    @Override
    public List<ResumeVO> getList(Criteria cri) {

        log.info("resume getList...........service");

        return resumeMapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal() {

        log.info("resume getTotal...........service");

        return resumeMapper.getTotalCount();
    }
}
