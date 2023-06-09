package com.chatgpt.demo;

import com.chatgpt.demo.enums.ApiEndPoint;
import com.chatgpt.demo.model.ProductVo;
import com.chatgpt.demo.model.ReviewAnswerVo;
import com.chatgpt.demo.model.ReviewVo;
import com.chatgpt.demo.service.ChatGptClient;
import com.chatgpt.demo.service.TaiaiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application.properties"})
class ChatGptClientTest {

    @Autowired
    private ChatGptClient chatGptClient;
    @Autowired
    private TaiaiService taiaiService;

    @Test
    void callConversationApi(){
        String question = "여행하면 떠오르는 키워드 5개 얘기해줘.";
        ResponseEntity<Map> response = chatGptClient.callOpenAiApi(ApiEndPoint.COMPLETION_ENDPOINT, ChatGptClient.getRequestBodyConversation(question, 0.5f, 1000));
        System.out.println(response);
    }

    @Test
    void callReviewKeywords(){
        List<String> keyword = taiaiService.getReviewKeyword(makeSampleReview());
        System.out.println(keyword);
    }

    @Test
    void callReviewAutoAnswer(){
        ReviewAnswerVo answer = taiaiService.getAutoAnswer(makeSampleReview());
        System.out.println(answer.getContents());
    }

    @Test
    void callGuideReviews() {
        ProductVo product = new ProductVo();
        product.setProductType("테마파크 입장권");
        List<String> reviewQuestion = taiaiService.getGuideQuestion(product);
        System.out.println(reviewQuestion);
    }

    ReviewVo makeSampleReview(){
        ReviewVo review = new ReviewVo();
        review.setContents("오사카 유니버셜 스튜디오 입장권을 구매했습니다. 개인적으로 익스프레스패스 보다 입장권이 가성비가 좋은 것 같아요. " +
                "닌텐도 월드 오픈런만 성공하면 모든 구역을 입장할 수 있습니다. 다만, 조금만 늦어도 닌텐도 월드는 들어갈 수 없으니 여유있게 가고 싶은 분들은 익스프레스패스를 추천드려요. " +
                "저는 입장권 구매하고 오픈 1시간 전에 가서 기다렸고 대부분의 어트랙션은 싱글라이더로 탑승해서 생각보다 쾌적하게 즐기고 왔습니다.");
        return review;
    }
}