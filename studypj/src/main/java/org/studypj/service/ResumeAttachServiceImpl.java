package org.studypj.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studypj.domain.ResumeAttachVO;
import org.studypj.mapper.ResumeAttachMapper;

@Service
@Log4j
public class ResumeAttachServiceImpl implements ResumeAttachService{

    @Setter(onMethod_ = @Autowired)
    private ResumeAttachMapper resumeAttachMapper;

    @Override
    public boolean register(ResumeAttachVO resumeAttach) {
        log.info("ResumeAttachService...... register ");
        return resumeAttachMapper.insert(resumeAttach) == 1;
    }

    @Override
    public boolean remove(int resume_no) {
        return false;
    }

    @Override
    public boolean modify(ResumeAttachVO resumeAttach) {
        return false;
    }

    @Override
    public ResumeAttachVO get(int resume_no) {
        log.info("ResumeAttachService...... get.. " + resume_no);

        return resumeAttachMapper.findByResumeNo(resume_no);
    }
}
