package com.task.news.feed.infrastructure.client.xkcd.response;

public class XkcdEntry {
    private final Integer num;
    private final String day;
    private final String month;
    private final String year;
    private final String title;
    private final String img;
    private final String link;

    public XkcdEntry(Integer num, String day, String month, String year, String title, String img, String link) {
        this.num = num;
        this.day = day;
        this.month = month;
        this.year = year;
        this.title = title;
        this.img = img;
        this.link = link;
    }

    public Integer getNum() {
        return num;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getImg() {
        return img;
    }

    public String getLink() {
        return link;
    }
}
