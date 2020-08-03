package it.fbonfadelli.manga.archive;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MangaController {

    @PostMapping(value = "/create")
    public void create(@RequestBody MangaController mangaController) {
        System.out.println("hello world");
    }
}
