package com.task.news.feed.application.handler.last_entries;

import com.task.news.feed.domain.entity.Entry;
import reactor.core.publisher.Flux;

public class LastEntriesResponse {
    private Flux<Entry> feed;

    private LastEntriesResponse(Flux<Entry> feed) {
        this.feed = feed;
    }

    public Flux<Entry> getFeed() {
        return feed;
    }

    public static LastEntriesResponse create(Flux<Entry> feed){
        return new LastEntriesResponse(feed);
    }
}
