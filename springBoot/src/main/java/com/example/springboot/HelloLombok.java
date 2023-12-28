package com.example.springboot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class HelloLombok {
    private final String hello;
    private final int lombok;

    public static void main(String[] args) {



        HelloLombok helloLombok = new HelloLombok("헬로", 5);


//        HelloLombok 생성자와 같음
//        public HelloLombok(String hello, int lombok) {
//            this.hello = hello;
//            this.lombok = lombok;
//        }
        System.out.println(helloLombok.getHello());
        System.out.println(helloLombok.getLombok());
    }
}
