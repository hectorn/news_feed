package com.task.news.feed.infrastructure.repository.xkcd;

import com.task.news.feed.NewsFeedTest;
import com.task.news.feed.domain.entity.Entry;
import com.task.news.feed.infrastructure.client.xkcd.XkcdWebClient;
import com.task.news.feed.infrastructure.client.xkcd.response.XkcdEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ApiXkcdRepositoryTest extends NewsFeedTest {

    @Mock
    private XkcdWebClient xkcdWebClient;

    private ApiXkcdRepository sut;

    @BeforeEach
    void setUp() {
        this.sut = new ApiXkcdRepository(xkcdWebClient);
    }

    @Test
    public void givenAGetLastRequestThenLAstEntryIsRetrieved(){
        when(xkcdWebClient.getLast()).thenReturn(Mono.just(anApiEntry()));

        var result = sut.getLast().block();

        assertNotNull(result);
        checkEntry(result);
    }

    @Test
    public void givenFindRequestThenEntryWithEntryIdIsRetrieved(){
        when(xkcdWebClient.find(123)).thenReturn(Mono.just(anApiEntry()));

        var result = sut.find(123).block();

        assertNotNull(result);
        checkEntry(result);
    }

    private void checkEntry(Entry entry) {
        assertEquals(123, entry.getId());
        assertEquals("title", entry.getTitle());
        assertEquals("link", entry.getWebUrl());
        assertEquals("img", entry.getPictureUrl());
        assertEquals("2021-01-12", entry.getDate().toString());
    }

    private XkcdEntry anApiEntry() {
        return new XkcdEntry(123,"12", "1","2021", "title", "img", "link");
    }
}