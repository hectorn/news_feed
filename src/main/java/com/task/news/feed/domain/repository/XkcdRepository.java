package com.task.news.feed.domain.repository;

import com.task.news.feed.domain.entity.Entry;
import reactor.core.publisher.Mono;

public interface XkcdRepository {
    Mono<Entry> getLast();

    Mono<Entry> find(Integer entryId);
}
