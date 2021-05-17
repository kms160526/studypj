package org.studypj.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.studypj.domain.PersonalVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
@Log4j
public class PersonalMapperTests {

    @Setter(onMethod_ = @Autowired)
    private PersonalMapper mapper;

//    public int insert(PersonalVO personal);
    @Test
    public void testInsert(){

        PersonalVO vo = new PersonalVO();
        // 추가안하고 insert 하면 null 값이 들어감
        vo.setName_ko("테스트");
        vo.setName_en("testName_en");
        vo.setName_cn("한자");
        vo.setBirth("19970508");
        vo.setAddress("테스트주소");
        vo.setContact("01022223333");
        vo.setPhone("01077778888");
        vo.setEmail("test@gmail.com");

        boolean result = mapper.insert(vo) == 1;

        log.info("result : " + result);
    }

//    public int delete(int personal_no);
    @Test
    public void testDelete(){

        int targetNo = 2;

        boolean result = mapper.delete(targetNo) == 1;

        log.info("delete test result : " + result);

    }

//    public int update(PersonalVO personal);
    @Test
    public void testUpdate(){

        PersonalVO vo = new PersonalVO();
        // update는 필수로 vo에 personal_no가 있음
        // 셋팅하지않으면 기본 초기화값이 0이므로 0이 들어감
        vo.setPersonal_no(4);

        vo.setName_ko("테스트 update");
        vo.setName_en("testName_en update");
        vo.setName_cn("한자");
        vo.setBirth("19970508");
        vo.setAddress("테스트주소");
        vo.setContact("01022223333");
        vo.setPhone("01077778888");
        vo.setEmail("test@gmail.com");

        boolean result = mapper.update(vo) == 1;

        log.info("update test result : " + result);

    }

//    public PersonalVO read(int personal_no);
    @Test
    public void testRead(){

        int targetNo = 4;

        PersonalVO vo = mapper.read(targetNo);

        log.info( "personal vo -> " + vo);
    }

    @Test
    public void testRecentRead(){

        PersonalVO vo = mapper.recentRead();

        log.info("personal vo -> " + vo);
    }





}
