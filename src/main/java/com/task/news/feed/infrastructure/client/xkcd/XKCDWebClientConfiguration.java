package com.task.news.feed.infrastructure.client.xkcd;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class XKCDWebClientConfiguration {

    @Bean(name = "xkcd-webclient")
    public WebClient webClient(
            @Value("${client.xkcd.host}") String host,
            ObjectMapper objectMapper
    ) {
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper)))
                .build();

        return WebClient.builder()
                .baseUrl(host)
                .exchangeStrategies(exchangeStrategies)
                .build();
    }
}
