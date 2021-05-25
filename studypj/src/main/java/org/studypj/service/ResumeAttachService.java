package org.studypj.service;

import org.studypj.domain.EducationVO;
import org.studypj.domain.ResumeAttachVO;

public interface ResumeAttachService {
    public boolean register(ResumeAttachVO resumeAttach);

    public boolean remove(int resume_no);

    public boolean modify(ResumeAttachVO resumeAttach);

    public ResumeAttachVO get(int resume_no);

}
