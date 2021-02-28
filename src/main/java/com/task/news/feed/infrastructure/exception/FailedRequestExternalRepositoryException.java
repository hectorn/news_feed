package com.task.news.feed.infrastructure.exception;

public class FailedRequestExternalRepositoryException extends InfrastructureException {

    public static final String CODE = "FAILED_REQUEST_EXTERNAL_REPOSITORY";
    public static final String MESSAGE = "Failed request to external repository. Cause: %s";

    public FailedRequestExternalRepositoryException(String cause) {
        super(CODE, String.format(MESSAGE, cause));
    }
}
