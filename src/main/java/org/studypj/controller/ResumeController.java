package org.studypj.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.studypj.domain.Criteria;
import org.studypj.domain.PageDTO;
import org.studypj.domain.PersonalStatementVO;
import org.studypj.service.PersonalStatementService;

import java.util.List;

@Controller
@Log4j
@RequestMapping("/resume/*")
@AllArgsConstructor
public class ResumeController {

    private PersonalStatementService personalStatementService;

    // GET - /resume/personalStatementList
    @GetMapping("/personalStatementList")
    public String personalStatementList(Model model, Criteria cri){
        // 자기소개서 목록을 출력해야 한다.
        log.info("list cri -> " + cri);

        List<PersonalStatementVO> list = personalStatementService.getList(cri);

        model.addAttribute("list", list);

        int total = personalStatementService.getTotal();

        log.info("personalStatement list에서의 total: " + total);

        model.addAttribute("pageMaker", new PageDTO(cri, total));

        log.info("personalStatement 진입");

        return "/resume/personalStatement";
    }

    // GET - /resume/personalStatement
    @GetMapping({"/getPersonalStatement", "/modifyPersonalStatement"})
    public void getPersonalStatement(@RequestParam("personal_statement_no") int personal_statement_no,
                                     @ModelAttribute("cri") Criteria cri, Model model){

        log.info("/getPersonalStatement... /modifyPersonalStatement .... controller");

        model.addAttribute("personalStatement", personalStatementService.get(personal_statement_no));
    }


    // GET - /resume/resume
    @GetMapping
    public void resume(){
        log.info("resume 진입");
    }


}
