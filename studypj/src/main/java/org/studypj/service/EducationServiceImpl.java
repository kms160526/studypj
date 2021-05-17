package org.studypj.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.studypj.domain.EducationVO;
import org.studypj.mapper.EducationMapper;

@Service
@Log4j
public class EducationServiceImpl implements EducationService{

    @Setter(onMethod_ = @Autowired)
    private EducationMapper educationMapper;

    @Override
    public boolean register(EducationVO education) {

        log.info("Education register.............");

        boolean registerResult = educationMapper.insert(education) == 1;

        return registerResult;
    }

    @Override
    public boolean remove(int education_group_no) {

        log.info("Education remove............ education_group_no -> " + education_group_no);

        boolean removeResult = educationMapper.delete(education_group_no) == 1;

        return removeResult;
    }

    @Override
    public boolean modify(EducationVO education) {

        boolean modifyResult = educationMapper.update(education) == 1;

        log.info("Education modify...............  result : " + modifyResult);

        return modifyResult;
    }

    @Override
    public EducationVO get(int education_group_no) {

        log.info("Education get..............");

        return educationMapper.read(education_group_no);
    }

    @Override
    public EducationVO recentGet() {

        log.info("Education recentGet............");

        return educationMapper.recentRead();
    }
}
