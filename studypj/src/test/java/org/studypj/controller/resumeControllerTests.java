package org.studypj.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.studypj.domain.Criteria;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/dispatcher-servlet.xml" })
@Log4j
public class resumeControllerTests {
    @Setter(onMethod_ = @Autowired)
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }


    @Test
    public void testGetResume() throws Exception{

//        Criteria cri = new Criteria(1,10);

        mockMvc.perform(get("/resume/getResume")
                .param("pageNum", "2")
                .param("account", "10")
                .param("resume_no", "1"))
                .andExpect(status().is(200));
    }


}
