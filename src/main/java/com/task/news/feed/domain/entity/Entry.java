package com.task.news.feed.domain.entity;

import java.time.LocalDate;

public class Entry {
    private final Integer id;
    private final String title;
    private final String pictureUrl;
    private final String webUrl;
    private final LocalDate date;

    private Entry(Integer id, String title, String pictureUrl, String webUrl, LocalDate date) {
        this.id = id;
        this.title = title;
        this.pictureUrl = pictureUrl;
        this.webUrl = webUrl;
        this.date = date;
    }

    public Integer getId() {
        return id;
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

    public LocalDate getDate() {
        return date;
    }


    public static final class Builder {
        private Integer id;
        private String title;
        private String pictureUrl;
        private String webUrl;
        private LocalDate date;

        private Builder() {
        }

        public static Builder anEntry() {
            return new Builder();
        }

        public Builder withId(Integer id) {
            this.id = id;
            return this;
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
            this.date = date;
            return this;
        }

        public Entry build() {
            return new Entry(id, title, pictureUrl, webUrl, date);
        }
    }
}
