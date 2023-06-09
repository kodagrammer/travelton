package com.chatgpt.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewAnswerVo {
    private ReviewVo review;
    private UserVo createUser;
    private String contents;
    private LocalDateTime createTime;
}
