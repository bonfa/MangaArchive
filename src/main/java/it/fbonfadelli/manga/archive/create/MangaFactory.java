package it.fbonfadelli.manga.archive.create;

public class MangaFactory {
    public Manga createFrom(String id, CreateMangaRequest createMangaRequest) {
        return new Manga(id, createMangaRequest.getTitle(), createMangaRequest.getAuthor());
    }
}
