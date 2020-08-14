package it.fbonfadelli.manga.archive.create;

import it.fbonfadelli.manga.archive.CreateMangaRequestAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manga")
public class MangaController {

    private final CreateMangaUseCase createMangaUseCase;
    private final CreateMangaRequestAdapter createMangaRequestAdapter;

    public MangaController(CreateMangaUseCase createMangaUseCase, CreateMangaRequestAdapter createMangaRequestAdapter) {
        this.createMangaUseCase = createMangaUseCase;
        this.createMangaRequestAdapter = createMangaRequestAdapter;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> create(@RequestBody MangaDto mangaDto) {
        CreateMangaRequest adapt = createMangaRequestAdapter.adapt(mangaDto);
        return createMangaUseCase.execute(adapt)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

}
