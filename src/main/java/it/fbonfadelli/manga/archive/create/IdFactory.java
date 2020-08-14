package it.fbonfadelli.manga.archive.create;

import java.util.UUID;

public class IdFactory {
    public String make() {
        return UUID.randomUUID().toString();
    }
}
