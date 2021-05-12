package org.studypj.controller;


import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j
@RequestMapping("/home/*")
public class HomeController {


    @GetMapping
    public void home(){
        log.info("home 진입");
    }



}
