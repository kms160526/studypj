package org.studypj.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.studypj.domain.TrainingVO;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
@Log4j
public class TrainingMapperTests {

    @Setter(onMethod_ = @Autowired)
    private TrainingMapper mapper;

//    // insert Training
//    public int insert(TrainingVO training);
    @Test
    public void testInsert(){
        // TrainingVO
//        private int training_group_no;
//        private int training_no;
//        private String training_term;
//        private String training_name;
//        private int training_completion;
//        private Date regdate;
        TrainingVO vo = new TrainingVO();
        vo.setTraining_no(23);
        vo.setTraining_term("20012002");
        vo.setTraining_name("training insert test");
        vo.setTraining_completion(1);   // 1은 수료, 2는 수료x, 후에 radio 로 처리하면 될것같다.

        boolean insertResult = mapper.insert(vo) == 1;

        log.info("test training insert result : " + insertResult);
    }

//
//    // delete Training
//    public int delete(int training_group_no);
    @Test
    public void testDelete(){

        int targetNo = 3;

        boolean deleteResult = mapper.delete(targetNo) == 1;

        log.info("test training delete result : " + deleteResult);
    }

//
//    // update Training
//    public int update(TrainingVO training);
    @Test
    public void testUpdate(){

        int targetNo = 4;

        TrainingVO vo = new TrainingVO();
        vo.setTraining_group_no(targetNo);
        vo.setTraining_no(5555);
        vo.setTraining_term("20102103");
        vo.setTraining_name("trainingUpdateTest");
        vo.setTraining_completion(2); // 1은 수료, 2는 수료x

        boolean updateResult = mapper.update(vo) == 1;

        log.info("test training update result : " + updateResult);
    }

//
//    // read - Training
//    public TrainingVO read(int training_group_no);
    @Test
    public void testRead(){

        int targetNo = 1;

        TrainingVO vo = mapper.read(targetNo);

        log.info("test training Read -> " + vo);
    }





}
