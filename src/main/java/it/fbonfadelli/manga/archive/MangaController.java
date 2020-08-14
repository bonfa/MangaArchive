package it.fbonfadelli.manga.archive;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manga")
public class MangaController {

    private final IdGenerator idGenerator;

    public MangaController(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<String> create(@RequestBody CreateMangaRequest createMangaRequest) {
        return ResponseEntity.ok(idGenerator.generateId());
    }
}
