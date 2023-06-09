package com.chatgpt.demo.controller;

import com.chatgpt.demo.service.TaiaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/gptAPI")
@RestController
public class GptController {
    @Autowired
    TaiaiService taiaiService;

}
