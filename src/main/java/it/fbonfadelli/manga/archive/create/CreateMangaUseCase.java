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

    public Optional<Id> execute(CreateMangaRequest createMangaRequest) {
        Id id = idFactory.make();
        return Optional.of(id)
                .map(i -> mangaFactory.createFrom(i, createMangaRequest))
                .map(mangaRepository::save)
                .filter(Boolean::booleanValue)
                .map(s -> id);
    }

}
