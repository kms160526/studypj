package org.studypj.mapper;

import org.studypj.domain.Criteria;
import org.studypj.domain.PersonalStatementVO;

import java.util.List;

public interface PersonalStatementMapper {

    // getList PersonalStatement
    public List<PersonalStatementVO> getList();

    // insert PersonalStatement
    public int insert(PersonalStatementVO vo);

    // delete PersonalStatement
    public int delete(int personal_statement_no);

    // update PersonalStatement
    public int update(PersonalStatementVO vo);

    // read - selectOne
    public PersonalStatementVO read(int personal_statement_no);

    // 페이징 처리 관련
    // total
    public int getTotalCount();

    // getListWithPaging
    // getListWithPaging PersonalStatement
    // getList 페이징 처리
    public List<PersonalStatementVO> getListWithPaging(Criteria cri);


}
