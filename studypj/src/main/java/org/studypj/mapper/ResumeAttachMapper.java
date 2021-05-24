package org.studypj.mapper;

import org.studypj.domain.ResumeAttachVO;

import java.util.List;

public interface ResumeAttachMapper {
    public int insert(ResumeAttachVO resumeAttach);

    public void delete(String uuid);

    public ResumeAttachVO findByResumeNo(int resume_no);

    public void deleteAll(int resume_no);

}
