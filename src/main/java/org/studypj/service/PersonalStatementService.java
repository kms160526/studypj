package org.studypj.service;

import org.studypj.domain.Criteria;
import org.studypj.domain.PersonalStatementVO;

import java.util.List;

public interface PersonalStatementService {

    public void register(PersonalStatementVO personalStatement);

    public boolean remove(int personal_statement_no);

    public boolean modify(PersonalStatementVO personalStatement);

    public PersonalStatementVO get(int personal_statement_no);

    //    public List<PersonalStatementVO> getList();
    //    페이징 처리 추가
    public List<PersonalStatementVO> getList(Criteria cri);

    //     페이징 처리 관련 getTotal
    public int getTotal();

}
