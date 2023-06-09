package com.chatgpt.demo.service;

import com.chatgpt.demo.enums.ApiEndPoint;
import com.chatgpt.demo.model.ProductVo;
import com.chatgpt.demo.model.ReviewAnswerVo;
import com.chatgpt.demo.model.ReviewVo;
import com.chatgpt.demo.model.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaiaiService {

    private final ChatGptClient chatGptClient;

    private String requestCompletion(String question) {
        return chatGptClient.getText(chatGptClient.callOpenAiApi(ApiEndPoint.COMPLETION_ENDPOINT, ChatGptClient.getRequestBodyConversation(question, 0.5f, 1000)));
    }

    public List<String> getGuideQuestion(ProductVo productVo) {
        List<String> questionList = new ArrayList<>();

        StringBuilder question = new StringBuilder();
        // 가이드 질문 추출해줘, 라는 질의 생성
        question.append("판매한 상품 정보가 아래와 같아.\n");
        question.append(productVo.getProductType());
        question.append("\n이 상품을 이용한 고객이 후기를 작성할 수 있도록 가이드 질문을 5가지만 뽑아줘\n" +
                "가이드 질문은 간결하고 정중하게 해줘");

        String result = requestCompletion(question.toString());

        for(String sentence : result.split("\n")) {
            if(sentence.contains(".")){
                String[] items = sentence.split("\\.");
                if(items.length == 2){
                    try{
                        Integer.parseInt(items[0]);
                        questionList.add(items[1].trim());
                    } catch(NumberFormatException nfe){
                        continue;
                    }
                }
            }
        }
        return questionList;
    }

    public List<String> getReviewKeyword(ReviewVo reviewVo) {
        List<String> keywords = new ArrayList<>();

        StringBuilder question = new StringBuilder();
        question.append("우리는 여행상품 판매 플랫폼이야.\n");
        question.append("오사카에서 사용할 수 있는 유니버셜스튜디오 입장권 상품에 달린 리뷰에 대해 요약된 키워드 5개를 아래 형태로 구분해서 뽑아줘.\n");
        question.append("키워드:키워드1,키워드2,키워드3,키워드4,키워드5");
        question.append("리뷰 내용은 아래와 같아.\n\n");
        question.append(reviewVo.getContents());

        String result = requestCompletion(question.toString());

        for(String sentence : result.split(":")) {
            if(sentence.contains(",")){
                String[] items = sentence.split(",");
                for(String item : items) {
                    keywords.add(item.trim());
                }
            }
        }

        return keywords;
    }

    public ReviewAnswerVo getAutoAnswer(ReviewVo reviewVo){
        StringBuilder question = new StringBuilder();
        // 답변 달아줘, 라는 질의 생성
        question.append("우리는 여행상품 판매 플랫폼이야.\n");
        question.append("오사카에서 사용할 수 있는 유니버셜스튜디오 입장권 상품에 달린 리뷰에 대해 정중하게 답변을 작성해줘.\n");
        question.append("리뷰 내용은 아래와 같아.\n\n");
        question.append(reviewVo.getContents());

        String result = requestCompletion(question.toString());

        UserVo admin = new UserVo();
        admin.setUserId("admin");
        admin.setUserName("투어비스");

        ReviewAnswerVo reviewAnswer = new ReviewAnswerVo();
        reviewAnswer.setReview(reviewVo);
        reviewAnswer.setCreateUser(admin);
        reviewAnswer.setContents(result);
        reviewAnswer.setCreateTime(LocalDateTime.now());

        return reviewAnswer;
    }
}
