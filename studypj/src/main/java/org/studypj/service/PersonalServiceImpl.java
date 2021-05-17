package org.studypj.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studypj.domain.PersonalVO;
import org.studypj.mapper.PersonalMapper;


@Service
@Log4j
public class PersonalServiceImpl implements PersonalService{

    @Setter(onMethod_ = @Autowired)
    private PersonalMapper personalMapper;

    @Override
    public boolean register(PersonalVO personal) {

        log.info("personal register ......");

        boolean registerResult = personalMapper.insert(personal) == 1;

        return registerResult;
    }

    @Override
    public boolean remove(int personal_no) {

        log.info("personal remove....... personal_no : " + personal_no);

        boolean removeResult = personalMapper.delete(personal_no) == 1;

        return removeResult;
    }

    @Override
    public boolean modify(PersonalVO personal) {

        log.info("personal modify.......... ");

        // update 행이 1일때만 ture
        boolean modifyResult = personalMapper.update(personal) == 1;

        return modifyResult;
    }

    @Override
    public PersonalVO get(int personal_no) {

        log.info("personal get.............");

        return personalMapper.read(personal_no);
    }

    @Override
    public PersonalVO recentGet() {

        log.info("personal recentGet............");

        return personalMapper.recentRead();
    }
}
