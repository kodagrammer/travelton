package com.chatgpt.demo.service;

import com.chatgpt.demo.model.ProductVo;
import com.chatgpt.demo.model.ReviewVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyDataMaker {

    public List<ProductVo> getProductList(){
        return null;
    }

    public List<ReviewVo> getReviewList(ProductVo productVo) {
        if(productVo.getReviewList() == null) {
            productVo.setReviewList(makeReviewList(productVo));
        }
        return productVo.getReviewList();
    }

    public List<ReviewVo> makeReviewList(ProductVo productVo) {
        return null;
    }
}
