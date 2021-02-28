package com.task.news.feed.domain.exception;

public class EntryNotFoundException extends DomainException {
    public static final String CODE = "ENTRY_NOT_FOUND";
    public static final String MESSAGE = "Entry with id %s not found";

    public EntryNotFoundException(Integer entryId) {
        super(CODE, String.format(MESSAGE, entryId));
    }
}
