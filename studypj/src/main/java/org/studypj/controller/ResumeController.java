package org.studypj.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.studypj.domain.*;
import org.studypj.service.*;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@Log4j
@RequestMapping("/resume/*")
@AllArgsConstructor
public class ResumeController {

    private PersonalStatementService personalStatementService;
    private ResumeService resumeService;
    private PersonalService personalService;
    private EducationService educationService;
    private TrainingService trainingService;

    // 날짜 데이터 포맷을 위한 @InitBinder yyyy-MM-dd
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    }


    // --------- personalStatement ---------
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

    // GET - /resume/getPersonalStatement or /resume/modifyPersonalStatement
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
        // 해당 자기소개서를 참조하고 있는 이력서가 있을경우 추가 처리가 필요하다.
        // 방법1 -> 이런 경우가 발생할 경우 실제로 삭제처리를 하지 않고, 새로운 테이블을 만들어서 flag 값을 확인한다. DB에 테이블추가 or 컬럼추가 update로 처리한다.
        // 방법2 -> 이런 경우가 발생할 때 작업을 취소한다. -> 삭제할 수 없다는 메세지를 보낸다.

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


    // --------- resume ---------
    // GET - /resume/resumeList
    @GetMapping("/resumeList")
    public String resumeList(Model model, Criteria cri){

        log.info("resume 진입");
        // resume 리스트
        // personalStatementList와 같은 방식으로 처리한다.

        // 이력서 목록을 출력해야 한다.
        // 이력서 목록 페이지 계산을 위한 cri
        log.info("list cri -> " + cri);

        // 페이지에서 startRownum을 계산하지 않음
        cri.setStartRownum((cri.getPageNum() -1) * cri.getAmount());

        List<ResumeVO> list = resumeService.getList(cri);

        model.addAttribute("list", list);

        int total = resumeService.getTotal();

        log.info("resume list에서의 total: " + total);

        model.addAttribute("pageMaker", new PageDTO(cri, total));

        return "/resume/resumeList";

    }

    // GET - modify or get resume
    @GetMapping({"/modifyResume", "/getResume"})
    public void getResume(@RequestParam("resume_no") int resume_no, @ModelAttribute("cri") Criteria cri, Model model){

        log.info("/getResume... or /modifyResume cri -> " + cri);

        // resume 의 정보들을 가져옴
        // resume --> resume_no, personal_statement_no, education_group_no, personal_no, training_group_no, regdate
        // 각각의 _no를 이용해서  자기소개서, 교육, 개인신상, 교육연수 정보를 얻어온다.
        ResumeVO resume = resumeService.get(resume_no);
        // get 전에 resume 체크
        log.info("/getResume -> resume : " + resume);
        model.addAttribute("resume", resume);

        // 개인신상 정보 얻어오기
        // resume 의 personal_no 를 참고하여 personal의 값들을 가져온다.
        // 값이 없는 경우에 대해서는 register 할때 처리해준다.
        PersonalVO personal = personalService.get(resume.getPersonal_no());
        // get 전에 personal 체크
        log.info("/getResume -> personal : " + personal);
        model.addAttribute("personal", personal);


        // 교육 정보 얻어오기 -> 최종 대학정보만 가져온다. -> 편입과정이 있을 수 있고... 대학원 과정이 있을 수 있다.
        // List<EducationVO> 타입일 것이다... -> 후에 수정
        // resume 의 personal_no 를 참고하여 education 값들을 가져온다.
        EducationVO education = educationService.get(resume.getEducation_group_no());
        log.info("/getResume -> education : " + education);
        model.addAttribute("education", education);

        // 교육연수 정보 얻어오기 -> 여러개이겠지만 일단은 한개로 작성..
        // -> List<Training> 타입으로 받아올 것 같다.
        TrainingVO training = trainingService.get(resume.getTraining_group_no());
        log.info("/getResume -> training : " + training);
        model.addAttribute("training", training);

        // 자기소개서 얻어오기 -> 추후 수정할땐 등록된 자기소개서중에 고르는 형식으로 수정하기
        // 자기소개서는 1개만 작성
        PersonalStatementVO personalStatement = personalStatementService.get(resume.getPersonal_statement_no());
        log.info("/getResume -> personalStatement : " + personalStatement);
        model.addAttribute("personalStatement", personalStatement);
    }

    // POST - modify resume -> redirect 처리
    @PostMapping("/modifyResume")
    public String modifyResume(ResumeVO resume,
                               @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
        // modify resume
        // 파일의 변경이 있을경우의 처리 ( 사진 )
        // 개인싱상정보의 수정
        // 학력사항의 수정
        // 교육사항의 수정
        // 선택된 자기소개서의 수정

        // Criteria 의 getListLink() 메서드를 활용하여 자동으로 추가된 url 생성
        return "redirect:/resume/resumeList" + cri.getListLink();
    }

    // POST - remove resume

    // GET - register resume

    // POST - register resume


}
