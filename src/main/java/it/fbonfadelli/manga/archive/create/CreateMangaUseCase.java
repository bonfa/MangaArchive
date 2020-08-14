package it.fbonfadelli.manga.archive.create;

import java.util.Optional;

public class CreateMangaUseCase {
    private final IdFactory idFactory;
    private final MangaRepository mangaRepository;
    private final MangaFactory mangaFactory;

    public CreateMangaUseCase(IdFactory idFactory, MangaFactory mangaFactory, MangaRepository mangaRepository) {
        this.idFactory = idFactory;
        this.mangaRepository = mangaRepository;
        this.mangaFactory = mangaFactory;
    }

    public Optional<String> execute(CreateMangaRequest createMangaRequest) {
        String id = idFactory.make();
        Manga manga = mangaFactory.createFrom(id, createMangaRequest);
        return Optional.of(mangaRepository.save(manga))
                .filter(Boolean::booleanValue)
                .map(s -> id);
    }

}
