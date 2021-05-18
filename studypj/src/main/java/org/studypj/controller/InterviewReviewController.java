package org.studypj.controller;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.studypj.domain.*;
import org.studypj.service.InterviewReviewService;
import org.studypj.service.InterviewService;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@Log4j
@RequestMapping("/interviewReview/*")
@AllArgsConstructor
public class InterviewReviewController {

    private InterviewService interviewService;
    private InterviewReviewService interviewReviewService;

    // 날짜 데이터 포맷을 위한 @InitBinder yyyy-MM-dd
    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
    }

    // 리스트 출력 페이지
    // interview의 리스트를 뿌려주는것에 주의
    // GET - interviewReviewList
    @GetMapping("/interviewReviewList")
    public String interviewReviewList(Model model, Criteria cri){

        log.info("GET /interviewReviewList........");

        // cri 계산 - startRownum 설정
        cri.setStartRownum((cri.getPageNum() -1) * cri.getAmount());

        // interview 목록중에 progress가 2번인 면접이 진행 완료된 목록들만 출력
        // 인터뷰 후기 작성 목록도 체크해야한다. 비교해서 후기가 없는 것만 작성해야 한다.
        // progress가 2인 것들과 interviewList를 left outer join 을 해서 interview_no가 0인것을 찾아서
        // 0이면 작성버튼을, 이미 있으면 수정 버튼을 표시한다.
        List<InterviewJoinVO> interviewJoinList = interviewService.getListWithProgress(cri);

        model.addAttribute("interviewJoinList", interviewJoinList);

        int total = interviewReviewService.getTotal();

        log.info("interview with progress 2 -> total : " + total);

        model.addAttribute("pageMaker", new PageDTO(cri, total));

        return "/interviewReview/interviewReviewList";
    }


    // 리스트 클릭시 이동
    // modify 와 register만 존재하므로 get은 필요없다.
    // GET - modifyInterviewReview
    @GetMapping("/modifyInterviewReview")
    public void modifyInterviewReview(@RequestParam("interview_no") int interview_no,
                                      @ModelAttribute("cri") Criteria cri, Model model){

        log.info("modifyInterviewReview");

        // 받아온 interview_no 를 이용해서 intreview_review 데이터를 가져온다.
        model.addAttribute("interviewReview", interviewReviewService.get(interview_no));

    }

    // POST - modifyInterviewReview

    // POST - removeInterviewReview

    // POST - registerInterviewReview




}
