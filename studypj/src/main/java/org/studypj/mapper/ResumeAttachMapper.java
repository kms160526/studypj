package org.studypj.mapper;

import org.studypj.domain.ResumeAttachVO;

import java.util.List;

public interface ResumeAttachMapper {
    public void insert(ResumeAttachVO vo);

    public void delete(String uuid);

    public ResumeAttachVO findByResumeNo(int resume_no);

    public void deleteAll(int resume_no);

}
