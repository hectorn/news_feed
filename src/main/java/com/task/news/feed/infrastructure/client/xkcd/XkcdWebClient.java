package com.task.news.feed.infrastructure.client.xkcd;

import com.task.news.feed.domain.exception.EntryNotFoundException;
import com.task.news.feed.infrastructure.client.xkcd.response.XkcdEntry;
import com.task.news.feed.infrastructure.exception.FailedRequestExternalRepositoryException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class XkcdWebClient {

    private final WebClient webClient;

    public XkcdWebClient(@Qualifier("xkcd-webclient") WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<XkcdEntry> getLast() {
        return webClient.get()
                .uri("/info.0.json")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError,
                        (response) -> Mono.error(new FailedRequestExternalRepositoryException(response.statusCode().toString())))
                .bodyToMono(XkcdEntry.class);
    }

    public Mono<XkcdEntry> find(Integer entryId) {
        return webClient.get()
                .uri("/{entryId}/info.0.json", entryId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::isError, (response) -> {
                    if (HttpStatus.NOT_FOUND.equals(response.statusCode())) {
                        return Mono.error(new EntryNotFoundException(entryId));
                    }
                    return Mono.error(new FailedRequestExternalRepositoryException(response.statusCode().toString()));
                })
                .bodyToMono(XkcdEntry.class);
    }
}
