package com.example.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // HelloController 클래스가 컨트롤러의 기능을 수행한다는 의미이다
public class HelloController {
    @GetMapping("/hello") // http://localhost:8080/hello
    @ResponseBody() // hello 메서드의 응답 결과가 문자열 그 자체임을 나타낸다.

    public String hello() {
        return "HelloWorld zxczxczxc";
    }
}
