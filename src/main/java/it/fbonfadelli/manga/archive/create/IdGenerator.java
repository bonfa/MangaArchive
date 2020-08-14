package it.fbonfadelli.manga.archive.create;

import java.util.UUID;

public class IdGenerator {
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
