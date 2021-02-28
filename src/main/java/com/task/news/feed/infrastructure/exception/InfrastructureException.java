package com.task.news.feed.infrastructure.exception;

public abstract class InfrastructureException extends RuntimeException {
    protected String code;
    protected String message;

    public InfrastructureException(String code, String message) {
        super(message);

        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
