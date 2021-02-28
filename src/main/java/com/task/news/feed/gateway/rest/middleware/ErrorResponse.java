package com.task.news.feed.gateway.rest.middleware;

public class ErrorResponse {
    private final String code;
    private final String message;

    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public static ErrorResponse of(String code, String message){
        return new ErrorResponse(code,message);
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
