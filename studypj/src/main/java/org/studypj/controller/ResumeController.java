package org.studypj.controller;


import com.google.gson.Gson;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.studypj.domain.*;
import org.studypj.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private ResumeAttachService resumeAttachService;

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

        // 성공시 result 를 success 로 실패시 fail
        rttr = resultCheckMethod(personalStatementService.modify(personalStatement), rttr);

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

        rttr = resultCheckMethod(personalStatementService.remove(personal_statement_no), rttr);

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

        rttr = resultCheckMethod(personalStatementService.register(personalStatement), rttr);

        // register는 처리 후 첫 페이지의 화면을 보여주면 좋을것같다.
        return "redirect:/resume/personalStatementList";
    }

    // GET - Ajax - /resume/getPersonalStatementAjax
    @RequestMapping(value = "/getPersonalStatementAjax", produces = "application/text; charset=utf8", method = RequestMethod.GET)
    @ResponseBody
    public String getPersonalStatementAjax(@RequestParam("personal_statement_no") int personal_statement_no) throws ParseException {
        log.info("getPersonalStatementAjax GET - personal_statement_no -> " + personal_statement_no);

        PersonalStatementVO personalStatement = personalStatementService.get(personal_statement_no);

        // Obj -> Json
        Gson gson = new Gson();
        log.info("personalStatementObj json -> " + gson.toJson(personalStatement));

        return gson.toJson(personalStatement);
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

        // 사진 관련 정보 얻어오기
        ResumeAttachVO resumeAttach = resumeAttachService.get(resume.getResume_no());
        if(resumeAttach != null){
            log.info("resume attach exitst");
            model.addAttribute("resumeAttach", resumeAttach);
        }


        // 개인신상 정보 얻어오기
        // resume 의 personal_no 를 참고하여 personal의 값들을 가져온다.
        // 값이 없는 경우에 대해서는 register 할때 처리해준다.
        PersonalVO personal = personalService.get(resume.getPersonal_no());
        // get 전에 personal 체크
        log.info("/getResume -> personal : " + personal);
        model.addAttribute("personal", personal);

        // 교육 정보 얻어오기 -> 최종 대학정보만 가져온다. -> 편입과정이 있을 수 있고... 대학원 과정이 있을 수 있다.
        // resume 의 personal_no 를 참고하여 education 값들을 가져온다.
        EducationVO education = educationService.get(resume.getEducation_group_no());
        log.info("/getResume -> education : " + education);
        model.addAttribute("education", education);

        // 교육연수 정보 얻어오기 -> 여러개이겠지만 일단은 한개로 작성..
        TrainingVO training = trainingService.get(resume.getTraining_group_no());
        log.info("/getResume -> training : " + training);
        model.addAttribute("training", training);

        // TODO: 자기소개서 얻어오기 -> 추후 수정할땐 등록된 자기소개서중에 고르는 형식으로 수정하기
        // 자기소개서는 1개만 작성
        PersonalStatementVO personalStatement = personalStatementService.get(resume.getPersonal_statement_no());
        log.info("/getResume -> personalStatement : " + personalStatement);
        model.addAttribute("personalStatement", personalStatement);

        // 수정 페이지일 경우 personalStatement의 목록들을 가져와야한다.
        List<PersonalStatementVO> personalStatementList = personalStatementService.getListNonParam();
        model.addAttribute("personalStatementList", personalStatementList);
    }

    // POST - modify resume -> redirect 처리
    @PostMapping("/modifyResume")
    public String modifyResume(ResumeVO resume, PersonalVO personal, PersonalStatementVO personalStatement,
                               EducationVO education, TrainingVO training,
                               @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){

        // 값 불러오는지 테스트
        log.info("POST -- /modifyResume");
        log.info("ResumeVO -> " + resume);
        log.info("PersonalVO -> " + personal);
        log.info("PersonalStatementVO -> " + personalStatement);
        log.info("EducationVO -> " + education);
        log.info("TrainingVO -> " + training);

        // 파일의 변경이 있을경우의 처리 ( 사진 )

        // TODO: 유효성 체크를 하고 modify를 시도해야한다.
        // 개인싱상정보의 유효성체크
        // 학력사항의 유효성체크
        // 교육사항의 유효성체크

        // 선택된 자기소개서의 수정 -> 따로 처리 ..
        // 전체 resume 수정 처리 ?
        rttr = resultCheckMethod(resumeService.modify(resume, personal, education, training), rttr);

        // Criteria 의 getListLink() 메서드를 활용하여 자동으로 추가된 url 생성
        return "redirect:/resume/resumeList" + cri.getListLink();
    }

    // POST - remove resume
    @PostMapping("/removeResume")
    public String removeResume(@RequestParam("resume_no") int resume_no,
                               @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){

        log.info("POST...... /removeResume ");

        rttr = resultCheckMethod(resumeService.remove(resume_no), rttr);

        //TODO: 파일 삭제 관련 작업 추가

        return "redirect:/resume/resumeList" + cri.getListLink();
    }

    // GET - register resume
    @GetMapping("/registerResume")
    public void registerResume(Model model){

        log.info("GET....... /registerResume");

        // 기본적으로 register는 기존의 가장 최신 정보를 가져와서 작성하고 새로운 _no 로 insert 한다.
        // 만약 최근의 데이터가 없다면 ? -> update가 아니라 insert 하는 작업을 실행해야 한다.

        // 가장 최신의 정보를 가져온다.
        PersonalVO personal = personalService.recentGet();
        EducationVO education = educationService.recentGet();
        TrainingVO training = trainingService.recentGet();

        // TODO : null 값일때 테스트 진행해야함
        // 신상정보
        if(personal != null){
            model.addAttribute("personal", personal);
        }else{
            personal = new PersonalVO();
            model.addAttribute("personal", personal);
        }

        // education
        if(education != null){
            model.addAttribute("education", education);
        }else{
            education = new EducationVO();
            model.addAttribute("education", education);
        }

        // training
        if(training != null){
            model.addAttribute("training", training);
        }else{
            training = new TrainingVO();
            model.addAttribute("training", training);
        }

        // 자소서 목록들을 불러온다.
        List<PersonalStatementVO> personalStatementList = personalStatementService.getListNonParam();
        model.addAttribute("personalStatementList", personalStatementList);

    }

    // POST - register resume
    @PostMapping("/registerResume")
    public String registerResume(ResumeVO resume, PersonalVO personal, PersonalStatementVO personalStatement,
                                 EducationVO education, TrainingVO training, ResumeAttachVO resumeAttach, RedirectAttributes rttr){

        log.info("POST... /registerResume.." + resume );

        log.info("grated 가져오는지 체크 -> " + education.getGrated());
        log.info("training -> " + training);

        // 사진 관련 처리
        // resumeAttach 잘 가져오나 테스트
        log.info("resumeAttach -> " + resumeAttach);


        // TODO: 유효성 체크를 하고 register를 시도해야한다.
        // 개인싱상정보의 유효성체크
        // 학력사항의 유효성체크
        // 교육사항의 유효성체크

        // 이력서 등록
        // 추가 -> resumeAttach 정보도 같이 전달하여 처리한다.
        rttr = resultCheckMethod(resumeService.register(resume, personal, personalStatement, education, training, resumeAttach), rttr);

        // register는 처리 후 첫 페이지의 화면을 보여주면 좋을것같다.
        return "redirect:/resume/resumeList";
    }


    // 처리결과가 성공이면 succecc, 실패면 fail
    // RedirectAttributes rttr 파라미터로 받아옴
    public RedirectAttributes resultCheckMethod(boolean result , RedirectAttributes rttr){
        if(result){
            rttr.addFlashAttribute("result", "success");
        }else{
            rttr.addFlashAttribute("result", "fail");
        }

        return rttr;
    }

}
