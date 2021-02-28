package com.task.news.feed.application.handler.last_entries;

import com.task.news.feed.application.handler.Handler;
import com.task.news.feed.domain.entity.Entry;
import com.task.news.feed.domain.repository.XkcdRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Comparator;

@Service
public class LastEntriesHandler implements Handler<LastEntriesResponse> {

    private final XkcdRepository xkcdRepository;

    public LastEntriesHandler(XkcdRepository xkcdRepository) {
        this.xkcdRepository = xkcdRepository;
    }

    @Override
    public LastEntriesResponse handle() {

        return LastEntriesResponse.create(
                xkcdRepository.getLast()
                        .flatMapMany(item ->
                                Flux.range(item.getId() - 9, 9).
                                        flatMap(xkcdRepository::find)
                                        .concatWith(Flux.just(item))
                        ).sort(Comparator.comparing(Entry::getDate)));
    }
}
