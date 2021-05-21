package org.studypj.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.studypj.domain.*;
import org.studypj.service.InterviewService;

import java.io.*;
import java.net.*;
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
    @CrossOrigin(origins = "*")
    @GetMapping("/getInterviewDirection")
    public void getInterviewDirection(@RequestParam("interview_no") int interview_no,
                                @ModelAttribute("cri") Criteria cri, Model model) throws URISyntaxException, IOException {

        log.info("GET- /getInterviewDirection....");

        model.addAttribute("interview", interviewService.get(interview_no));

        // https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving
        // start={출발지}&goal={목적지}&option={탐색옵션}

//            xhr.setRequestHeader("X-NCP-APIGW-API-KEY-ID","eyjh3k2c64");
//            xhr.setRequestHeader("X-NCP-APIGW-API-KEY","lNUkTFWZxP9r8ZpBONTYaSf5bRsdsvZhrrou0oi0");

        // restTemplate

//        start : "127.1054065,37.3595669",
//                goal : "126.9784147,37.5666805"

//        String re = naverAPI();
//        log.info("api호출 결과 :::::: " + re);


    }

//    // 이동경로 좌표 반환 - 네이버 길찾기 API
//    @GetMapping("/naverAPI")
//    @CrossOrigin(origins = "*")
//    @ResponseBody
//    public ResponseEntity<String> naverAPI() throws URISyntaxException, IOException {
//
//        // URL 인코딩..
//        URI redirectUri2 = new URI("https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start%3d%7b127.1054065%2c37.3595669%7d%26goal%3d%7b126.9784147%2c37.5666805%7d");
//        String url = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start%3d%7b127.1054065%2c37.3595669%7d%26goal%3d%7b126.9784147%2c37.5666805%7d";
//
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        headers.add("X-NCP-APIGW-API-KEY-ID","eyjh3k2c64");
//        headers.add("X-NCP-APIGW-API-KEY","lNUkTFWZxP9r8ZpBONTYaSf5bRsdsvZhrrou0oi0");
//
//        HttpEntity<String> request = new HttpEntity<>(headers);
//
////        String res = restTemplate.getForObject(redirectUri2, request);
////        log.info("res ------------>>>>>>>>>> " + res);
//
//        ResponseEntity<String> response = restTemplate.exchange(
//                redirectUri2, HttpMethod.GET, request, String.class);
////        // 네이버 API URL
////        URL redirectUri = new URL("https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start%3d%7b127.1054065%2c37.3595669%7d%26goal%3d%7b126.9784147%2c37.5666805%7d");
////
////        HttpURLConnection conn = (HttpURLConnection) redirectUri.openConnection();
////
////        conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID","eyjh3k2c64");
////        conn.setRequestProperty("X-NCP-APIGW-API-KEY","lNUkTFWZxP9r8ZpBONTYaSf5bRsdsvZhrrou0oi0");
////        conn.setRequestProperty("Content-Type", "application/json");
////
////        // request에 JSON 데이터 준비
////        conn.setDoOutput(true);
////        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
////
////        // commands라는 JSONArray를 담을 JSONObject 생성
//////        JsonObject commands = new JsonObject();
//////        commands.put("commands", commands)
////
////        JsonObject responseJson = null;
////        int responseCode = conn.getResponseCode();
////        log.info("responseCode -> " + responseCode);
////
////        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
////        StringBuilder sb = new StringBuilder();
////        String line = "";
////        while((line = br.readLine()) != null){
////            sb.append(line);
////        }
////
////        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.setLocation(redirectUri);
////        httpHeaders.add("X-NCP-APIGW-API-KEY-ID","eyjh3k2c64");
////        httpHeaders.add("X-NCP-APIGW-API-KEY","lNUkTFWZxP9r8ZpBONTYaSf5bRsdsvZhrrou0oi0");
//
////        @GetMapping("/https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start={출발지}&goal={목적지}")
//
////        return ResponseEntity.created(redirectUri).headers(httpHeaders).build();
////        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
////        return gson.toJson(sb.toString());
//
//        return response;
//
//    }



//    // 이동경로 좌표 반환 - 네이버 길찾기 API
//    @GetMapping("/naverAPI")
//    @CrossOrigin(origins = "*")
//    @ResponseBody
//    public String naverAPI() throws URISyntaxException, IOException {
//
////        // 네이버 API URL
//        String apiURL = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start=127.1058342,37.359708&goal=129.075986,35.179470";
//        URL redirectUri = new URL(apiURL);
//
//        HttpURLConnection conn = (HttpURLConnection) redirectUri.openConnection();
//
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID","eyjh3k2c64");
//        conn.setRequestProperty("X-NCP-APIGW-API-KEY","lNUkTFWZxP9r8ZpBONTYaSf5bRsdsvZhrrou0oi0");
//        conn.setRequestProperty("Content-Type", "application/json");
//
//        // request에 JSON 데이터 준비
//        conn.setDoOutput(true);
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//
//        // commands라는 JSONArray를 담을 JSONObject 생성
////        JsonObject commands = new JsonObject();
////        commands.put("commands", commands)
//
//        JsonObject responseJson = null;
//        int responseCode = conn.getResponseCode();
//        log.info("responseCode -> " + responseCode);
//
//        BufferedReader br;
//
//        if(responseCode==200) { // 정상 호출
//            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {  // 에러 발생
//            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//
//        StringBuilder sb = new StringBuilder();
//        StringBuffer sb2 = new StringBuffer();
//
//        String line = "";
//        while((line = br.readLine()) != null){
//            sb2.append(line);
//        }
//        br.close();
//        log.info(sb2.toString());
//
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
////        HttpHeaders httpHeaders = new HttpHeaders();
////        httpHeaders.add("X-NCP-APIGW-API-KEY-ID","eyjh3k2c64");
////        httpHeaders.add("X-NCP-APIGW-API-KEY","lNUkTFWZxP9r8ZpBONTYaSf5bRsdsvZhrrou0oi0");
//
////        @GetMapping("/https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start={출발지}&goal={목적지}")
//
//
//        return gson.toJson(sb);
////        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
////        return gson.toJson(sb.toString());
//
////        return response;
//
//    }



    // 이동경로 좌표 반환 - 네이버 길찾기 API
    @GetMapping("/naverAPI")
//    @CrossOrigin(origins = "*")
    @ResponseBody
    public String naverAPI() throws URISyntaxException, IOException {

        String url = "https://naveropenapi.apigw.ntruss.com/map-direction/v1/driving?start=127.1058342,37.359708&goal=129.075986,35.179470"; // encodeURIComponent로 인코딩 된 주소
        String clientId = "eyjh3k2c64";
        String clientSecret = "lNUkTFWZxP9r8ZpBONTYaSf5bRsdsvZhrrou0oi0";

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        request.addHeader("X-NCP-APIGW-API-KEY-ID", clientId);  //해더에 Clinet Id와 Client Secret을 넣습니다
        request.addHeader("X-NCP-APIGW-API-KEY", clientSecret);

        StringBuffer html = new StringBuffer();

        try {
            HttpResponse response = client.execute(request);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

            String current = "";

            while ((current = reader.readLine()) != null) {
                html.append(current);
            }

            reader.close();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return html.toString();
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
