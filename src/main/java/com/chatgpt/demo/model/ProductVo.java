package com.chatgpt.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductVo {
    private Integer productId;
    private String productType;
    private String category;
    private String location;
    private List<ReviewVo> reviewList;
}
