package it.fbonfadelli.manga.archive.create;

import java.util.Objects;

public class Manga {
    private final String id;
    private final String title;
    private final String author;

    public Manga(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Manga{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manga manga = (Manga) o;
        return Objects.equals(id, manga.id) &&
                Objects.equals(title, manga.title) &&
                Objects.equals(author, manga.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author);
    }
}
