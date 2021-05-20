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
import org.studypj.service.InterviewService;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/interviewPlace/*")
public class InterviewController {

    private InterviewService interviewService;

    // 날짜 데이터 포맷을 위한 @InitBinder yyyy-MM-dd
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    }

    // --------- interview ---------
    // GET - /interviewList
    // 리스트 출력 페이지
    @GetMapping("/interviewList")
    public String interviewList(Model model, Criteria cri){

        log.info("GET /interviewList........");

        // cri 계산 - startRownum 설정
        cri.setStartRownum((cri.getPageNum() -1) * cri.getAmount());

        List<InterviewVO> interviewList = interviewService.getList(cri);

        model.addAttribute("interviewList", interviewList);

        int total = interviewService.getTotal();

        log.info("interview with progress 2 -> total : " + total);

        model.addAttribute("pageMaker", new PageDTO(cri, total));

        return "/interviewPlace/interviewList";
    }

    // GET - /getInterview or /modifyInterview
    @GetMapping({"/getInterview", "/modifyInterview"})
    public void getInterview(@RequestParam("interview_no") int interview_no,
                             @ModelAttribute("cri") Criteria cri, Model model){

        log.info("GET- /getInterview.... or /modifyInterview");

        model.addAttribute("interview", interviewService.get(interview_no));
    }

    // POST - /modifyInterview
    @PostMapping("/modifyInterview")
    public String modifyInterview(InterviewVO interview,
                                  @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {

        log.info("POST... /modifyInterview..");

        rttr = resultCheckMethod(interviewService.modify(interview), rttr);

        return "redirect:/interviewPlace/interviewList" + cri.getListLink();
    }

    // POST - /removeInterview
    @PostMapping("/removeInterview")
    public String removeInterview(@RequestParam("interview_no") int interview_no,
                                  @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {

        log.info("POST...... /removeInterview..");

        rttr = resultCheckMethod(interviewService.remove(interview_no), rttr);

        return "redirect:/interviewPlace/interviewList" + cri.getListLink();
    }

    // GET - /registerInterview
    @GetMapping("/registerInterview")
    public void registerInterview(){

        log.info("GET....... /registerInterview");
    }

    // POST - /registerInterview
    @PostMapping("/registerInterview")
    public String registerInterview(InterviewVO interview, RedirectAttributes rttr){

        log.info("POST........ /registerInterview");

        // TODO: 면접처 주소와 관련된 추가 처리가 필요함. 좌표를 가지고 있는 것도 필요함

        rttr = resultCheckMethod(interviewService.register(interview), rttr);

        return "redirect:/interviewPlace/interviewList";
    }

    // 주소 관련 정보 팝업
    @RequestMapping("/jusoPopup")
    public void jusoPopup(){

//        return "/interviewPlace/jusoPopup";
    }


    // --------- interview 위치 ... /interviewMap ---------
    // GET - /interviewMapList -> 기존의 GET - /interviewList와 같은 형식으로 작성
    @GetMapping("/interviewMapList")
    public String interviewMapList(Model model, Criteria cri){

        log.info("GET /interviewMapList........");

        // cri 계산 - startRownum 설정
        cri.setStartRownum((cri.getPageNum() -1) * cri.getAmount());

        List<InterviewVO> interviewList = interviewService.getList(cri);

        model.addAttribute("interviewList", interviewList);

        int total = interviewService.getTotal();

        log.info("interview with progress 2 -> total : " + total);

        model.addAttribute("pageMaker", new PageDTO(cri, total));

        return "/interviewPlace/interviewMapList";
    }

    // GET - /getInterviewMap
    @GetMapping("/getInterviewMap")
    public void getInterviewMap(@RequestParam("interview_no") int interview_no,
                             @ModelAttribute("cri") Criteria cri, Model model){

        log.info("GET- /getInterviewMap....");

        model.addAttribute("interview", interviewService.get(interview_no));
    }


    // --------- interview 길찾기 /interviewDirection --------
    @GetMapping("/interviewDirectionList")
    public String interviewDirectionList(Model model, Criteria cri){

        log.info("GET /interviewDirectionList........");

        // cri 계산 - startRownum 설정
        cri.setStartRownum((cri.getPageNum() -1) * cri.getAmount());

        List<InterviewVO> interviewList = interviewService.getList(cri);

        model.addAttribute("interviewList", interviewList);

        int total = interviewService.getTotal();

        log.info("interview with progress 2 -> total : " + total);

        model.addAttribute("pageMaker", new PageDTO(cri, total));

        return "/interviewPlace/interviewDirectionList";
    }


    // GET - /getInterviewMap
    @GetMapping("/getInterviewDirection")
    public void getInterviewDirection(@RequestParam("interview_no") int interview_no,
                                @ModelAttribute("cri") Criteria cri, Model model){

        log.info("GET- /getInterviewDirection....");

        model.addAttribute("interview", interviewService.get(interview_no));
    }



    // 처리결과가 성공이면 succecc, 실패면 fail
    // RedirectAttributes rttr 파라미터로 받아옴
    public RedirectAttributes resultCheckMethod(boolean result, RedirectAttributes rttr) {
        if (result) {
            rttr.addFlashAttribute("result", "success");
        } else {
            rttr.addFlashAttribute("result", "fail");
        }

        return rttr;
    }
}
