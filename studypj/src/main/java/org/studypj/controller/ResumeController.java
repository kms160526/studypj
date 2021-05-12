package org.studypj.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.studypj.domain.Criteria;
import org.studypj.domain.PageDTO;
import org.studypj.domain.PersonalStatementVO;
import org.studypj.service.PersonalStatementService;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@Log4j
@RequestMapping("/resume/*")
@AllArgsConstructor
public class ResumeController {

    private PersonalStatementService personalStatementService;

    // 날짜 데이터 포맷을 위한 @InitBinder yyyy-MM-dd
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    }

    // GET - /resume/personalStatementList
    @GetMapping("/personalStatementList")
    public String personalStatementList(Model model, Criteria cri){
        // 자기소개서 목록을 출력해야 한다.
        log.info("list cri -> " + cri);

        // 페이지에서 startRownum을 계산하지 않음
        cri.setStartRownum((cri.getPageNum() -1) * cri.getAmount());

        List<PersonalStatementVO> list = personalStatementService.getList(cri);

        model.addAttribute("list", list);

        int total = personalStatementService.getTotal();

        log.info("personalStatement list에서의 total: " + total);

        model.addAttribute("pageMaker", new PageDTO(cri, total));

        log.info("personalStatement 진입");

        return "/resume/personalStatement";
    }

    // GET - /resume/personalStatement or /resume/modifyPersonalStatement
    @GetMapping({"/getPersonalStatement", "/modifyPersonalStatement"})
    public void getPersonalStatement(@RequestParam("personal_statement_no") int personal_statement_no,
                                     @ModelAttribute("cri") Criteria cri, Model model){
        log.info("cri -> " + cri);

        log.info("/getPersonalStatement... /modifyPersonalStatement .... controller");

        model.addAttribute("personalStatement", personalStatementService.get(personal_statement_no));
    }

    // POST - /resume/modifyPersonalStatement
    @PostMapping("/modifyPersonalStatement")
    public String modifyPersonalStatement(PersonalStatementVO personalStatement,
                                          @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
        // 등록일자 yyyy-MM-dd 데이터타입으로 맞춰주지 않으면 400번 에러가 발생한다.
        // update 작업이므로 redirect 처리를한다.

        log.info("POST... /modifyPersonalStatement..");

        if(personalStatementService.modify(personalStatement)){
            // 성공시 result 를 success 로
            rttr.addFlashAttribute("result", "success");
        }else{
            rttr.addFlashAttribute("result", "fail");
        }

        // Criteria 의 getListLink() 메서드를 활용하여 자동으로 추가된 url 생성
        return "redirect:/resume/personalStatementList" + cri.getListLink();
    }

    // POST - /resume/removePersonalStatement
    @PostMapping("/removePersonalStatement")
    public String removePersonalStatement(@RequestParam("personal_statement_no") int personal_statement_no,
            @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){

        log.info("POST...... /removePersonalStatement ");

        if(personalStatementService.remove(personal_statement_no)){
            // 성공시 result 를 success 로
            rttr.addFlashAttribute("result", "success");
        }else{
            rttr.addFlashAttribute("result", "fail");
        }

        return "redirect:/resume/personalStatementList" + cri.getListLink();
    }

    // GET - /resume/registerPersonalStatement
    @GetMapping("/registerPersonalStatement")
    public void registerPersonalStatement(){

        log.info("GET....... /registerPersonalStatement");
    }

    // POST - /resume/registerPersonalStatement
    @PostMapping("/registerPersonalStatement")
    public String registerPersonalStatement(PersonalStatementVO personalStatement, RedirectAttributes rttr){

        log.info("POST... /registerPersonalStatement.." + personalStatement );

        if(personalStatementService.register(personalStatement)){
            // 성공시 result 를 success 로
            rttr.addFlashAttribute("result", "success");
        }else{
            rttr.addFlashAttribute("result", "fail");
        }

        // register는 처리 후 첫 페이지의 화면을 보여주면 좋을것같다.
        return "redirect:/resume/personalStatementList";
    }



    // GET - /resume/resumeList
    @GetMapping
    public void resumeList(){

        log.info("resume 진입");
        // resume 리스트
    }


}
