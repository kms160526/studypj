package org.studypj.mapper;

import org.studypj.domain.Criteria;
import org.studypj.domain.PersonalStatementVO;
import org.studypj.domain.PersonalVO;
import org.studypj.domain.ResumeVO;

import java.util.List;

public interface ResumeMapper {

    // insert Resume
    public int insert(ResumeVO resume);

    // delete Resume
    public int delete(int resume_no);

    // update Resume
    public int update(ResumeVO vo);

    // read - selectOne
    public ResumeVO read(int resume_no);

    // 페이징 처리 관련
    // total
    public int getTotalCount();

    // getListWithPaging
    // getListWithPaging PersonalStatement
    // getList 페이징 처리
    public List<ResumeVO> getListWithPaging(Criteria cri);

    // recentRead - 첨부파일의 처리 관련
    public ResumeVO recentRead();
}
