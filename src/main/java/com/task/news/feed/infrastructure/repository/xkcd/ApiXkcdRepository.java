package com.task.news.feed.infrastructure.repository.xkcd;

import com.task.news.feed.domain.entity.Entry;
import com.task.news.feed.domain.repository.XkcdRepository;
import com.task.news.feed.infrastructure.client.xkcd.XkcdWebClient;
import com.task.news.feed.infrastructure.client.xkcd.response.XkcdEntry;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
public class ApiXkcdRepository implements XkcdRepository {

    private final XkcdWebClient xkcdWebclient;

    public ApiXkcdRepository(XkcdWebClient xkcdWebclient) {
        this.xkcdWebclient = xkcdWebclient;
    }

    @Override
    public Mono<Entry> getLast() {
        return xkcdWebclient.getLast().map(this::toEntry);
    }

    @Override
    public Mono<Entry> find(Integer entryId) {
        return xkcdWebclient.find(entryId).map(this::toEntry);
    }

    private Entry toEntry(XkcdEntry xkcdEntry) {
        return Entry.Builder.anEntry()
                .withId(xkcdEntry.getNum())
                .withDate(getDate(xkcdEntry))
                .withTitle(xkcdEntry.getTitle())
                .withWebUrl(xkcdEntry.getLink())
                .withPictureUrl(xkcdEntry.getImg())
                .build();
    }

    private LocalDate getDate(XkcdEntry xkcdEntry) {
        return LocalDate.of(
                Integer.parseInt(xkcdEntry.getYear()),
                Integer.parseInt(xkcdEntry.getMonth()),
                Integer.parseInt(xkcdEntry.getDay()));
    }
}
