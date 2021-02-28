package com.task.news.feed.application.handler.last_entries;

import com.task.news.feed.NewsFeedTest;
import com.task.news.feed.domain.repository.XkcdRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static com.task.news.feed.domain.entity.EntryMother.anEntryBuilder;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class LastEntriesHandlerTest extends NewsFeedTest {

    @Mock
    private XkcdRepository repository;

    private LastEntriesHandler sut;

    @BeforeEach
    public void setUp() {
        this.sut = new LastEntriesHandler(repository);
    }

    @Test
    public void givenAHandleRequestThenAListOfEntriesIsRetrieved(){
        when(repository.getLast()).thenReturn(Mono.just(anEntryBuilder().build()));
        when(repository.find(anyInt())).thenReturn(Mono.just(anEntryBuilder().build()));

        var result = sut.handle().getFeed().collectList().block();

        assertNotNull(result);
        assertEquals(10, result.size());
    }

    @Test
    public void givenAHandleRequestThenAListOfEntriesIsWithExactlyLast10ItemsIsRetrieved(){
        when(repository.getLast()).thenReturn(Mono.just(anEntryBuilder().build()));
        when(repository.find(123)).thenReturn(Mono.just(anEntryBuilder().withId(123).build()));
        when(repository.find(122)).thenReturn(Mono.just(anEntryBuilder().withId(122).build()));
        when(repository.find(121)).thenReturn(Mono.just(anEntryBuilder().withId(121).build()));
        when(repository.find(120)).thenReturn(Mono.just(anEntryBuilder().withId(120).build()));
        when(repository.find(119)).thenReturn(Mono.just(anEntryBuilder().withId(119).build()));
        when(repository.find(118)).thenReturn(Mono.just(anEntryBuilder().withId(118).build()));
        when(repository.find(117)).thenReturn(Mono.just(anEntryBuilder().withId(117).build()));
        when(repository.find(116)).thenReturn(Mono.just(anEntryBuilder().withId(116).build()));
        when(repository.find(115)).thenReturn(Mono.just(anEntryBuilder().withId(115).build()));

        var result = sut.handle().getFeed().collectList().block();

        assertNotNull(result);
        assertEquals(10, result.size());
        assertEquals(115, result.get(0).getId());
        assertEquals(116, result.get(1).getId());
        assertEquals(117, result.get(2).getId());
        assertEquals(118, result.get(3).getId());
        assertEquals(119, result.get(4).getId());
        assertEquals(120, result.get(5).getId());
        assertEquals(121, result.get(6).getId());
        assertEquals(122, result.get(7).getId());
        assertEquals(123, result.get(8).getId());
        assertEquals(124, result.get(9).getId());

    }

    @Test
    public void givenAHandleRequestThenAListOfOrderedEntriesIsRetrieved(){
        when(repository.getLast()).thenReturn(Mono.just(anEntryBuilder().build()));
        when(repository.find(123)).thenReturn(Mono.just(anEntryBuilder().withId(123).withDate(LocalDate.now().minusDays(2)).build()));
        when(repository.find(not(eq(123)))).thenReturn(Mono.just(anEntryBuilder().withDate(LocalDate.now().plusDays(2)).build()));

        var result = sut.handle().getFeed().collectList().block();

        assertNotNull(result);
        assertEquals(123, result.get(0).getId());
        assertEquals(124, result.get(1).getId());
    }
}