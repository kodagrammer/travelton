package com.chatgpt.demo.service;

import com.chatgpt.demo.enums.ApiEndPoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@PropertySource("classpath:apikey.properties")
public class ChatGptClient {
    @Value("${apikey}")
    private String API_KEY;
    @Value("${api.uri}")
    private String API_URI;

    public ResponseEntity<Map> callOpenAiApi(ApiEndPoint apiEndPoint, Map<String, Object> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(API_URI + apiEndPoint.getEndPoint(), requestEntity, Map.class);
//        return response.getBody();
    }

    public String getText(ResponseEntity<Map> response){
        Map<String, Object> responseBody = response.getBody();
        List<Map<String, Object>> choicesList = (List<Map<String, Object>>)responseBody.get("choices");
        Map<String, Object> choiceMap = choicesList.get(0);
        return (String)choiceMap.get("text");
    }

    public String getImageUrl(ResponseEntity<Map> response){
        Map<String, Object> responseBody = response.getBody();
        List<Map<String,Object>> dataList = (List<Map<String,Object>>)responseBody.get("data");
        return (String)(dataList.get(0).get("url"));
    }

    public static Map<String, Object> getRequestBodyConversation(String prompt, float temperature, int maxTokens) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model","text-davinci-003");
        requestBody.put("prompt", prompt);
        requestBody.put("temperature", temperature);
        requestBody.put("max_tokens", maxTokens);
        return requestBody;
    }

    public static Map<String, Object> getRequestBodyEdits(String prompt, String instruction) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model","text-davinci-edit-001");
        requestBody.put("input", prompt);
        requestBody.put("instruction", instruction);
        return requestBody;
    }

    public static Map<String, Object> getRequestBodyImage(String prompt) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("prompt", prompt);
        requestBody.put("size","1024x1024");
        requestBody.put("n", 1);
        requestBody.put("response_format", "url");
        return requestBody;
    }
}
