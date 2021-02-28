package com.task.news.feed.gateway.rest.controller;

import com.task.news.feed.application.handler.Handler;
import com.task.news.feed.application.handler.last_entries.LastEntriesResponse;
import com.task.news.feed.domain.entity.Entry;
import com.task.news.feed.gateway.rest.dto.EntryDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static com.task.news.feed.gateway.rest.dto.EntryDto.Builder.anEntryDto;

@RestController
@RequestMapping("/api/v1/entries")
public class EntriesController {

    private final Handler<LastEntriesResponse> handler;

    EntriesController(Handler<LastEntriesResponse> handler) {
        this.handler = handler;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Flux<EntryDto>> getLastItems() {

        var response = handler.handle();

        return ResponseEntity.ok(response.getFeed().map(this::toEntryDto));
    }

    private EntryDto toEntryDto(Entry entry) {
        return anEntryDto()
                .withTitle(entry.getTitle())
                .withPictureUrl(entry.getPictureUrl())
                .withWebUrl(entry.getWebUrl())
                .withDate(entry.getDate())
                .build();
    }
}
