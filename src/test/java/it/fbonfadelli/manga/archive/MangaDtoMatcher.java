package it.fbonfadelli.manga.archive;

import it.fbonfadelli.manga.archive.create.MangaDto;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class MangaDtoMatcher implements Matcher<MangaDto> {
    private final MangaDto expected;

    public static MangaDtoMatcher matches(MangaDto expected) {
        return new MangaDtoMatcher(expected);
    }

    private MangaDtoMatcher(MangaDto expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object obj) {
        if (!(obj instanceof MangaDto)) {
            return false;
        }

        MangaDto actual = (MangaDto) obj;

        return actual.author.equals(expected.author)
                && actual.title.equals(expected.title);
    }

    @Override
    public void describeMismatch(Object o, Description description) {

    }

    @Override
    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

    }

    @Override
    public void describeTo(Description description) {

    }
}
