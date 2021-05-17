package org.studypj.mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.studypj.domain.InterviewReviewVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
@Log4j
public class InterviewReviewMapperTests {

    @Setter(onMethod_ = @Autowired)
    private InterviewReviewMapper mapper;

    @Test
    public void testInsert(){

        InterviewReviewVO vo = new InterviewReviewVO();
        vo.setInterview_no(1);
        vo.setInterview_review_content("테스트");

        log.info("test Insert result -> " +  mapper.insert(vo));
    }

    @Test
    public void testUpdate(){
        // update의 _no는 interview_review_no임에 주의
        int targetNo = 1;

        InterviewReviewVO vo = new InterviewReviewVO();
        vo.setInterview_review_no(targetNo);
        vo.setInterview_no(1);
        vo.setInterview_review_content("테스트 update");

        log.info("test Update result -> " + mapper.update(vo));

    }

    @Test
    public void testRead(){

        // _no가 Interview_no 를 사용하는 것에 주의
        int targetNo = 1;

        log.info("test read result -> " + mapper.read(targetNo));
    }
//
//    <update id="update">
//    update INTERVIEW_REVIEW
//    set INTERVIEW_REVIEW_CONTENT     = #{interview_review_content}
//    where INTERVIEW_REVIEW_NO = #{interview_review_no}
//    </update>
//
//    <!-- 따로 join을 진행하지 않고, 어짜피 1개의 데이터가 있을 것이므로 interview의 _no 정보를 활용하여 조회 -->
//    <select id="read" resultType="org.studypj.domain.InterviewReviewVO">
//    select * from INTERVIEW_REVIEW where INTERVIEW_NO = #{interview_no}
//    </select>
}
