package it.fbonfadelli.manga.archive.domain.create.manga;

public class CreateMangaRequest {
    private final String title;
    private final String author;

    public CreateMangaRequest(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
