package org.studypj.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.studypj.domain.Criteria;
import org.studypj.domain.InterviewVO;
import org.studypj.domain.PersonalStatementVO;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
@Log4j
public class InterviewMapperTests {

    @Setter(onMethod_ = @Autowired)
    private InterviewMapper mapper;

//    private int interview_no;
//    private String interview_name;
//    private String interview_date;
//    private String interview_address;
//    private int interview_progress;
//
//    private Date interview_regdate;

    @Test
    public void testInsert() {

        InterviewVO vo = new InterviewVO();
        vo.setInterview_name("면접처이름");
        vo.setInterview_date("오늘");
        vo.setInterview_address("서울시 강북구");
        vo.setInterview_progress(2);

        log.info("insert InterviewVO -> " + vo);

        int result = mapper.insert(vo);

        log.info("insert result : " + result );

    }

    @Test
    public void testDelete(){

        int targetNo = 2;

        log.info("delete Interview targetNo -> " + targetNo);

        log.info("delete result : " + mapper.delete(targetNo));

    }

    @Test
    public void testUpdate(){

        int targetNo = 4;

        InterviewVO vo = new InterviewVO();
        vo.setInterview_no(targetNo);
        vo.setInterview_name("면접처이름 update");
        vo.setInterview_date("오늘 update");
        vo.setInterview_address("서울시 강북구 update");
        vo.setInterview_progress(1);

        log.info("update result : " + mapper.update(vo));

    }

    @Test
    public void testRead(){

        int targetNo = 2;

        log.info("read result -> " + mapper.read(targetNo));

    }

    @Test
    public void testGetTotalCount(){

        log.info("get total Count -> " + mapper.getTotalCount());
    }

    @Test
    public void testGetListWithPaging(){

        Criteria cri = new Criteria();

        List<InterviewVO> list = mapper.getListWithPaging(cri);

        log.info("get List With Paging -> ");

        list.forEach(interview -> log.info(interview));
    }


}
