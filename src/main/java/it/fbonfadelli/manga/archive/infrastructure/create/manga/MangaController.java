package it.fbonfadelli.manga.archive.infrastructure.create.manga;

import it.fbonfadelli.manga.archive.domain.create.manga.CreateMangaUseCase;
import it.fbonfadelli.manga.archive.domain.create.manga.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/manga")
public class MangaController {

    private final CreateMangaUseCase createMangaUseCase;
    private final CreateMangaRequestAdapter createMangaRequestAdapter;
    private final CreateMangaDtoValidator createMangaDtoValidator;
    private final CreateMangaValidationErrorFormatter createMangaValidationErrorFormatter;

    public MangaController(CreateMangaUseCase createMangaUseCase,
                           CreateMangaRequestAdapter createMangaRequestAdapter,
                           CreateMangaDtoValidator createMangaDtoValidator,
                           CreateMangaValidationErrorFormatter createMangaValidationErrorFormatter) {
        this.createMangaUseCase = createMangaUseCase;
        this.createMangaRequestAdapter = createMangaRequestAdapter;
        this.createMangaDtoValidator = createMangaDtoValidator;
        this.createMangaValidationErrorFormatter = createMangaValidationErrorFormatter;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> create(@RequestBody MangaDto mangaDto) {
        return createMangaDtoValidator.validate(mangaDto)
                .map(createMangaValidationErrorFormatter::format)
                .map(ResponseEntity.badRequest()::body)
                .orElseGet(() -> executeUseCase(mangaDto));
    }

    private ResponseEntity<String> executeUseCase(@RequestBody MangaDto mangaDto) {
        return Optional.of(mangaDto)
                .map(createMangaRequestAdapter::toCreateMangaRequest)
                .flatMap(createMangaUseCase::execute)
                .map(Id::getValue)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}
