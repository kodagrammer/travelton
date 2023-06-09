package com.chatgpt.demo.enums;

public enum ApiEndPoint {
    COMPLETION_ENDPOINT("/completions"),
    EDIT_ENDPOINT("/edits"),
    IMAGES_ENDPOINT("/images/generations"),
    MODEL_ENDPOINT("/models");

    private String _endPoint;
    private ApiEndPoint(String endPoint){
        _endPoint = endPoint;
    }
    public String getEndPoint() {
        return _endPoint;
    }
}
