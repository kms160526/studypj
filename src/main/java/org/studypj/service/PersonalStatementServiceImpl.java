package org.studypj.service;


import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studypj.domain.Criteria;
import org.studypj.domain.PersonalStatementVO;
import org.studypj.mapper.PersonalStatementMapper;

import java.util.List;


@Service
@Log4j
public class PersonalStatementServiceImpl implements PersonalStatementService{

    @Setter(onMethod_ = @Autowired)
    private PersonalStatementMapper personalStatementMapper;

    @Override
    public void register(PersonalStatementVO personalStatement) {

        log.info("personalStatement register..........");

        personalStatementMapper.insert(personalStatement);
    }

    @Override
    public boolean remove(int personal_statement_no) {

        log.info("personalStatement remove..........");

        boolean removeResult = personalStatementMapper.delete(personal_statement_no) == 1;

        return removeResult;
    }

    @Override
    public boolean modify(PersonalStatementVO personalStatement) {

        log.info("personalStatement register..........");

        boolean modifyResult = personalStatementMapper.update(personalStatement) == 1;

        return modifyResult;
    }

    @Override
    public PersonalStatementVO get(int personal_statement_no) {

        log.info("personalStatement get..........");

        return personalStatementMapper.read(personal_statement_no);
    }


//    페이징 처리 추가
//    @Override
//    public List<PersonalStatementVO> getList() {
//
//        log.info("personalStatement getList..........");
//
//        return personalStatementMapper.getList();
//    }

    @Override
    public List<PersonalStatementVO> getList(Criteria cri) {

        log.info("personalStatement getList..........withPaging");

        return personalStatementMapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal() {

        log.info("personalStatement getTotal ................");

        return personalStatementMapper.getTotalCount();
    }

}
