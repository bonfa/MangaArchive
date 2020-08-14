package it.fbonfadelli.manga.archive.domain.create.manga;

public class MangaFactory {
    public Manga createFrom(Id id, CreateMangaRequest createMangaRequest) {
        return new Manga(id, createMangaRequest.getTitle(), createMangaRequest.getAuthor()); //todo test
    }
}
