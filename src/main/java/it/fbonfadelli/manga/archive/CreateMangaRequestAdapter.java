package it.fbonfadelli.manga.archive;

import it.fbonfadelli.manga.archive.create.CreateMangaRequest;
import it.fbonfadelli.manga.archive.create.MangaDto;

public class CreateMangaRequestAdapter {
    public CreateMangaRequest adapt(MangaDto mangaDto) {
        return new CreateMangaRequest(mangaDto.title, mangaDto.author);
    }
}
