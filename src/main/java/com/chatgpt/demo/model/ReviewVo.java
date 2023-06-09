package com.chatgpt.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ReviewVo {
    ProductVo productVo;
    UserVo createUser;
    String contents;
    List<String> keywordList;
    ReviewAnswerVo answer;
    LocalDateTime createTime;
}
