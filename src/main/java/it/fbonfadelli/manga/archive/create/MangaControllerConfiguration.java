package it.fbonfadelli.manga.archive.create;

import it.fbonfadelli.manga.archive.CreateMangaDtoValidator;
import it.fbonfadelli.manga.archive.CreateMangaRequestAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MangaControllerConfiguration {

    @Bean
    public CreateMangaUseCase createMangaUseCase(IdFactory idFactory, MangaFactory mangaFactory, MangaRepository mangaRepository) {
        return new CreateMangaUseCase(idFactory, mangaFactory, mangaRepository);
    }

    @Bean
    public CreateMangaDtoValidator createMangaDtoValidator() {
        return new CreateMangaDtoValidator();
    }

    @Bean
    public MangaFactory getMangaFactory() {
        return new MangaFactory();
    }

    @Bean
    public IdFactory idFactory() {
        return new IdFactory();
    }

    @Bean
    public MangaRepository mangaRepository() {
        return new MangaRepository();
    }

    @Bean
    public CreateMangaRequestAdapter createMangaRequestAdapter() {
        return new CreateMangaRequestAdapter();
    }
}
