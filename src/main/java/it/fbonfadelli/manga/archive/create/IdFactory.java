package it.fbonfadelli.manga.archive.create;

import java.util.UUID;

public class IdFactory {
    public Id make() {
        return new Id(UUID.randomUUID().toString());
    }
}
