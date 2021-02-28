package com.task.news.feed.domain.entity;


import java.time.LocalDate;

public class EntryMother {

    public static Entry.Builder anEntryBuilder() {
        return Entry.Builder.anEntry()
                .withId(124)
                .withTitle("title")
                .withPictureUrl("pictureUrl")
                .withWebUrl("webUrl")
                .withDate(LocalDate.now());
    }
}
