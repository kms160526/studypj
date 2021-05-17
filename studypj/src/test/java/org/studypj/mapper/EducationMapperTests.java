package org.studypj.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.studypj.domain.EducationVO;
import org.studypj.domain.PersonalVO;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
@Log4j
public class EducationMapperTests {

    @Setter(onMethod_ = @Autowired)
    private EducationMapper mapper;

//    // insert Education
//    public int insert(EducationVO education);
    @Test
    public void testInsert(){
        // EducationVO
//        private int education_group_no;
//        private int education_no;
//        private String education_term;
//        private String school_name;
//        private int grated;
//
//        private Date regdate;

        EducationVO vo = new EducationVO();
        vo.setEducation_no(222);
        vo.setEducation_term("20012005");
        vo.setSchool_name("테스트대학");
        vo.setGrated(1);    // 졸업

        boolean insertResult = mapper.insert(vo) == 1;

        log.info("test Insert Education Result : " + insertResult);

    }

//
//    // delete Education
//    public int delete(int education_group_no);
    @Test
    public void testDelete(){

        int targetNo = 2;

        boolean deleteResult = mapper.delete(targetNo) == 1;

        log.info("test delete Education result : " + deleteResult);

    }

//
//    // update Education
//    public int update(EducationVO education);
    @Test
    public void testUpdate(){

        EducationVO vo = new EducationVO();
        // update 는 education_group_no가 필수
        vo.setEducation_group_no(3);

        vo.setEducation_no(555);
        vo.setEducation_term("20112012");
        vo.setSchool_name("update대학");
        vo.setGrated(2);    // 2는 재학중

        boolean updateResult = mapper.update(vo) == 1;

        if(updateResult){
            log.info("test update Education result : " + updateResult);
        }else{
            log.info("Education vo update fail -> vo.getEducation_group_no : " + vo.getEducation_group_no());
        }

    }

//
//    // read - Education
//    public EducationVO read(int education_group_no);
    @Test
    public void testRead(){

        int targetNo = 3;

        EducationVO vo = mapper.read(targetNo);

        if(vo == null){
            log.info(targetNo + " 에 해당하는 데이터가 없음");
        }else{
            log.info("test read Education result vo : " + vo);
        }

    }

    @Test
    public void testRecentRead(){

        EducationVO vo = mapper.recentRead();

        log.info("Education vo -> " + vo);
    }

}
