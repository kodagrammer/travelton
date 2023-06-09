package com.chatgpt.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("taiai/v1")
@RestController
public class TaiaiController {

    @GetMapping("/home")
    public String home(){
        return "hello Taiai!";
    }

    // 상품리스트 호출 컨트롤러

    // 상품리뷰 호출 컨트롤러

}
