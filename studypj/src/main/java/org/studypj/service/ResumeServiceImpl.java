package org.studypj.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studypj.domain.*;
import org.studypj.mapper.*;

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

    @Setter(onMethod_ = @Autowired)
    private PersonalStatementMapper personalStatementMapper;

    @Setter(onMethod_ = @Autowired)
    private ResumeAttachMapper resumeAttachMapper;


    @Override
    public boolean register(ResumeVO resume) {

        log.info("resume register...........service");

        boolean registerResult = resumeMapper.insert(resume) == 1;

        return registerResult;
    }

    // 실제 데이터가 들어있는 이력서 등록 메서드
    @Transactional
    @Override
    public boolean register(ResumeVO resume, PersonalVO personal, PersonalStatementVO personalStatement, EducationVO education, TrainingVO training, ResumeAttachVO resumeAttach) {

        log.info("resume register...........service");

        // 문제가 생겼을 경우 어디서 문제가 생겼는지 알기위해서 하나씩 체크한다.
        boolean registerResult = false;
        boolean registerSubResult = false;
        boolean registerResumeResult = false;
        boolean registerResumeAttachResult = false;

        // 신상정보, 자기소개서, 학력, 교육 정보 insert
        registerSubResult =  subRegister(personal, education, training);
        log.info("register Resume subRegister Result -> " + registerSubResult);

        // 최신정보 읽어오기
        personal = personalMapper.recentRead();
        education = educationMapper.recentRead();
        training = trainingMapper.recentRead();

        resume.setPersonal_no(personal.getPersonal_no());
        resume.setEducation_group_no(education.getEducation_group_no());
        resume.setTraining_group_no(training.getTraining_group_no());
        resume.setPersonal_statement_no(personalStatement.getPersonal_statement_no());
        log.info("register Resume 입력받은 resume의 정보 : " + resume);

        registerResumeResult = resumeMapper.insert(resume) == 1;
        log.info("registerResumeResult Result -> " + registerResumeResult);

        // 첨부파일이 없을경우 registerResumeAttachResult는 true로 한다.
        if(resumeAttach.getUuid() != null){
            // resume를 등록했기 때문에 resume_no가 생겼다.
            // 사진 관련된 정보 ATTACH 테이블에 추가한다.
            resume = resumeMapper.recentRead();
            resumeAttach.setResume_no(resume.getResume_no());

            registerResumeAttachResult = resumeAttachMapper.insert(resumeAttach) == 1;
            log.info("registerResumeAttachResult Result -> " + registerResumeAttachResult);
        }else{
            log.info("Attach Null");
            registerResumeAttachResult = true;
        }

        // 전부 정상적으로 처리되었다면 registerResult는 true;
        registerResult = registerResumeResult && registerResumeResult && registerSubResult && registerResumeAttachResult;
        log.info("register resume Result -> " + registerResult);

        return registerResult;
    }

    @Transactional
    @Override
    public boolean remove(int resume_no) {
        log.info("resume remove...........service");

        ResumeAttachVO resumeAttach = resumeAttachMapper.findByResumeNo(resume_no);

        if(resumeAttach != null){
           log.info("resumeAttach result -> " + (resumeAttachMapper.deleteAll(resume_no) == 1));
        }

        boolean removeResult = resumeMapper.delete(resume_no) == 1;

        return removeResult;
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

    // 이력서 등록을 위한 personal, personalStatement, education, training 등록 메서드, 트랜잭션 처리
    @Transactional
    public boolean subRegister(PersonalVO personal, EducationVO education, TrainingVO training){
        // 문제가 생겼을 경우 어디서 문제가 생겼는지 알기위해서 하나씩 체크한다.
        boolean registerResult = false;
        boolean registerPersonalResult = false;
        boolean registerEducationResult = false;
        boolean registerTrainingResult = false;

        // 이력서 등록 로직
        // _no 가 0이면 insert 아니면 update
        if(personal.getPersonal_no() != 0){
            registerPersonalResult = personalMapper.update(personal) == 1;
        }else if(personal.getPersonal_no() == 0){
            registerPersonalResult = personalMapper.insert(personal) == 1;
        }
        log.info("registerPersonal Result -> " + registerPersonalResult);

        if(education.getEducation_group_no() != 0){
            registerEducationResult = educationMapper.update(education) == 1;
        } else if(education.getEducation_group_no() == 0){
            registerEducationResult = educationMapper.insert(education) == 1;
        }
        log.info("registerEducationResult Result -> " + registerEducationResult);

        if(training.getTraining_group_no() != 0){
            registerTrainingResult = trainingMapper.update(training) == 1;
        } else if(training.getTraining_group_no() == 0){
            registerTrainingResult = trainingMapper.insert(training) == 1;
        }
        log.info("registerTrainingResult Result -> " + registerTrainingResult);

        registerResult = registerPersonalResult && registerEducationResult && registerTrainingResult;

        return registerResult;
    }

}
