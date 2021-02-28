package com.task.news.feed.gateway.rest.controller;

import com.task.news.feed.NewsFeedTest;
import com.task.news.feed.application.handler.Handler;
import com.task.news.feed.application.handler.last_entries.LastEntriesResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static com.task.news.feed.domain.entity.EntryMother.anEntryBuilder;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class EntriesControllerTest extends NewsFeedTest {

    @Mock
    private Handler<LastEntriesResponse> handler;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {

        EntriesController sut = new EntriesController(handler);

        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
    }

    @Test
    public void test() throws Exception {
        when(handler.handle()).thenReturn(LastEntriesResponse.create(Flux.just(anEntryBuilder().withDate(LocalDate.of(2021, 6, 1)).build())));

       var mvcResult= mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/entries"))
                .andExpect(request().asyncStarted())
                .andReturn();

       mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("[{\"title\":\"title\",\"pictureUrl\":\"pictureUrl\",\"webUrl\":\"webUrl\",\"date\":\"2021-06-01\"}]"));
    }
}