package org.studypj.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studypj.domain.Criteria;
import org.studypj.domain.PersonalStatementVO;
import org.studypj.domain.ResumeVO;
import org.studypj.mapper.ResumeMapper;

import java.util.List;

@Service
@Log4j
public class ResumeServiceImpl implements ResumeService{

    @Setter(onMethod_ = @Autowired)
    private ResumeMapper resumeMapper;


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

    // modify 일단 킵..
//    @Override
//    public boolean modify(ResumeVO resume) {
//
//        log.info("resume modify...........service");
//
//        boolean registerResult = resumeMapper.(resume) == 1;
//
//        return false;
//    }

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
