package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/sbb")
    @ResponseBody
    public String index() {
        System.out.println("index");
        return "안녕하세요. sbb에 오신 것을 환영합니다.";
    }

    @GetMapping("/")
    public String root() {
        // 홈으로 올 경우, 질문 리스트로 리다이렉트 시킴
        return "redirect:/question/list";
    }
}
