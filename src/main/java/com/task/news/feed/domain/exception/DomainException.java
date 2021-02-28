package com.task.news.feed.domain.exception;

public abstract class DomainException extends RuntimeException {
    protected String code;
    protected String message;

    public DomainException(String code, String message) {
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
