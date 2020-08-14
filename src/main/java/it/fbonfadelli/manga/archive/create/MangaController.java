package it.fbonfadelli.manga.archive.create;

import it.fbonfadelli.manga.archive.CreateMangaDtoValidator;
import it.fbonfadelli.manga.archive.CreateMangaRequestAdapter;
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
    private final ValidationErrorFormatter validationErrorFormatter;

    public MangaController(CreateMangaUseCase createMangaUseCase,
                           CreateMangaRequestAdapter createMangaRequestAdapter,
                           CreateMangaDtoValidator createMangaDtoValidator,
                           ValidationErrorFormatter validationErrorFormatter) {
        this.createMangaUseCase = createMangaUseCase;
        this.createMangaRequestAdapter = createMangaRequestAdapter;
        this.createMangaDtoValidator = createMangaDtoValidator;
        this.validationErrorFormatter = validationErrorFormatter;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> create(@RequestBody MangaDto mangaDto) {
        return createMangaDtoValidator.validate(mangaDto)
                .map(validationErrorFormatter::format)
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
