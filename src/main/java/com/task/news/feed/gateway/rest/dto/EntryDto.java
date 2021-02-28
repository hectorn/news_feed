package com.task.news.feed.gateway.rest.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EntryDto {
    private final String title;
    private final String pictureUrl;
    private final String webUrl;
    private final String date;

    public EntryDto(String title, String pictureUrl, String webUrl, String date) {
        this.title = title;
        this.pictureUrl = pictureUrl;
        this.webUrl = webUrl;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getDate() {
        return date;
    }

    public static final class Builder {
        private String title;
        private String pictureUrl;
        private String webUrl;
        private String date;

        private Builder() {
        }

        public static Builder anEntryDto() {
            return new Builder();
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
            return this;
        }

        public Builder withWebUrl(String webUrl) {
            this.webUrl = webUrl;
            return this;
        }

        public Builder withDate(LocalDate date) {
            this.date = date.format(DateTimeFormatter.ofPattern("y-MM-dd"));
            return this;
        }

        public EntryDto build() {
            return new EntryDto(title, pictureUrl, webUrl, date);
        }
    }
}
