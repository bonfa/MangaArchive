package it.fbonfadelli.manga.archive.infrastructure.create.manga;

import it.fbonfadelli.manga.archive.domain.create.manga.CreateMangaRequest;

public class CreateMangaRequestAdapter {
    public CreateMangaRequest toCreateMangaRequest(MangaDto mangaDto) {
        return new CreateMangaRequest(mangaDto.title, mangaDto.author);
    }
}
