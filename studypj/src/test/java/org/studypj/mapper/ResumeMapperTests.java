package org.studypj.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.studypj.domain.Criteria;
import org.studypj.domain.ResumeVO;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
@Log4j
public class ResumeMapperTests {

    @Setter(onMethod_ = @Autowired)
    private ResumeMapper mapper;

    // getTotalCount
    @Test
    public void testGetTotalCount(){

        log.info("Resume total count : " + mapper.getTotalCount());

    }

    // read
    @Test
    public void testRead(){
        ResumeVO vo = mapper.read(1);

        log.info("test resume vo : " + vo);
    }

    // insert
    @Test
    public void testInsert(){
        int result = 0;
        ResumeVO vo = new ResumeVO();
        vo.setPersonal_statement_no(20016);
        vo.setPersonal_no(1);
        vo.setEducation_group_no(1);
        vo.setTraining_group_no(1);
        log.info("insert 하기위한 vo " + vo);

        result = mapper.insert(vo);

        log.info("insert result : " + result);
    }

    // for insert
    @Test
    public void testForInsert(){

        for(int i=0; i<50; i++){
            int result = 0;
            ResumeVO vo = new ResumeVO();
            vo.setPersonal_statement_no(1);
            vo.setPersonal_no(1);
            vo.setEducation_group_no(1);
            vo.setTraining_group_no(1);
            log.info("insert 하기위한 vo " + vo);

            result = mapper.insert(vo);

            log.info("insert result : " + result);
        }
    }

    // delete test
    @Test
    public void testDelete(){

        int target = 27;
        int result = 0;

        result = mapper.delete(target);

        log.info("delete result : " + result);

    }

    // test getListWithPaging - resume
    @Test
    public void testGetListWithPaging(){
        Criteria cri = new Criteria();
        cri.setAmount(10);
        cri.setPageNum(1);
        cri.setStartRownum(0);

        List<ResumeVO> list = mapper.getListWithPaging(cri);

        list.forEach(resume -> log.info(resume));

    }

}
