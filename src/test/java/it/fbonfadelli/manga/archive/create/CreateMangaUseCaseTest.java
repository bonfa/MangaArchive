package it.fbonfadelli.manga.archive.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CreateMangaUseCaseTest {
    private static final Id ID = new Id("123");
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final Manga MANGA = new Manga(ID, TITLE, AUTHOR);
    private static final CreateMangaRequest CREATE_MANGA_REQUEST = new CreateMangaRequest(TITLE, AUTHOR);

    private IdFactory idFactory;
    private MangaFactory mangaFactory;
    private MangaRepository mangaRepository;
    private CreateMangaUseCase createMangaUseCase;

    @BeforeEach
    void setUp() {
        idFactory = Mockito.mock(IdFactory.class);
        mangaRepository = Mockito.mock(MangaRepository.class);
        mangaFactory = Mockito.mock(MangaFactory.class);
        createMangaUseCase = new CreateMangaUseCase(idFactory, mangaFactory, mangaRepository);
    }

    @Test
    void success() {
        when(idFactory.make()).thenReturn(ID);
        when(mangaFactory.createFrom(ID, CREATE_MANGA_REQUEST)).thenReturn(MANGA);
        when(mangaRepository.save(MANGA)).thenReturn(true);

        Optional<Id> uuid = createMangaUseCase.execute(CREATE_MANGA_REQUEST);

        assertThat(uuid).isEqualTo(Optional.of(ID));
    }

    @Test
    void error() {
        when(idFactory.make()).thenReturn(ID);
        when(mangaFactory.createFrom(ID, CREATE_MANGA_REQUEST)).thenReturn(MANGA);
        when(mangaRepository.save(MANGA)).thenReturn(false);

        Optional<Id> uuid = createMangaUseCase.execute(CREATE_MANGA_REQUEST);

        assertThat(uuid).isEmpty();
    }

}