package org.studypj.mapper;


import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.studypj.domain.Criteria;
import org.studypj.domain.PersonalStatementVO;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/applicationContext.xml")
@Log4j
public class PersonalStatementMapperTests {

    @Setter(onMethod_ = @Autowired)
    private PersonalStatementMapper mapper;

    @Test
    public void testGetList(){
        List<PersonalStatementVO> list = mapper.getList();

        list.forEach( vo -> log.info(vo));
    }

    @Test
    public void testInsert(){
        PersonalStatementVO vo = new PersonalStatementVO();
        int result = 0;

        vo.setExperiance("insert 테스트 경험2");
        vo.setHome_environment("insert 테스트 가정환경2");
        vo.setJob_espirations("insert 테스트 입사후포부2");
        vo.setPros_and_cons("insert 테스트 본인장단점2");

        result =  mapper.insert(vo);

        if(result > 0) { log.info("insert success"); }
    }

    // 더미 데이터 생성 테스트
    @Test
    public void testInsertMany(){
        for(int i=0; i<5000; i++){
            PersonalStatementVO vo = new PersonalStatementVO();
            int result = 0;

            vo.setExperiance("insert 테스트 경험 " +i );
            vo.setHome_environment("insert 테스트 가정환경 " + i);
            vo.setJob_espirations("insert 테스트 입사후포부 " + i);
            vo.setPros_and_cons("insert 테스트 본인장단점" + i);

            result =  mapper.insert(vo);

            if(result > 0) { log.info("insert success i: " + i); }

        }
    }


    @Test
    public void testDelete(){
        // 4번 personal_statement_no를 삭제
        // 만약 이미 resume에 등록되어 있는 personal_statement_no를 삭제한다면?
        // -> 참조키로 연결되어 있기 때문에 삭제할 수 없는 오류가 발생한다.

        int personal_statement_no = 20016;
        int result = 0;

        result = mapper.delete(personal_statement_no);
        log.info(result + " 개의 항목을 PERSONAL_STATEMENT 테이블에서 삭제하였습니다.");
    }

    @Test
    public void testUpdate(){
        // 5번 personal_statement_no 데이터를 수정
        int personal_statement_no = 5;

        PersonalStatementVO vo = new PersonalStatementVO();
        int result = 0;

        vo.setPersonal_statement_no(personal_statement_no);
        vo.setExperiance("update 테스트 경험 ");
        vo.setHome_environment("update 테스트 가정환경");
        vo.setJob_espirations("update 테스트 입사후포부");
        vo.setPros_and_cons("update 테스트 본인장단점");

        result =  mapper.update(vo);
        log.info("update personal_statement_no : " + personal_statement_no);
        log.info("update test result : " + result);
    }

    @Test
    public void testRead(){
        int personal_statement_no = 5;

        PersonalStatementVO vo = mapper.read(personal_statement_no);

        log.info(vo);
    }

    @Test
    public void testGetTotalCount(){

        log.info(mapper.getTotalCount());
    }

    @Test
    public void getListWithPaging(){
        Criteria cri = new Criteria(2,10);

        List<PersonalStatementVO> list = mapper.getListWithPaging(cri);

        list.forEach( vo -> log.info(vo));

    }


}
