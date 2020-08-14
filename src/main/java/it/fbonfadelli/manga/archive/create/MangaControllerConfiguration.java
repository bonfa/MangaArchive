package it.fbonfadelli.manga.archive.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MangaControllerConfiguration {
    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator();
    }
}
