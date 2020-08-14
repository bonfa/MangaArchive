package it.fbonfadelli.manga.archive;

import java.util.UUID;

public class IdGenerator {
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
