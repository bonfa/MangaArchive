package it.fbonfadelli.manga.archive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.fbonfadelli.manga.archive.create.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static it.fbonfadelli.manga.archive.MangaDtoMatcher.*;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class CreateMangaControllerTest {

    private static final String CREATION_ID = "CREATION_ID";
    private static final String TITLE = "TITLE";
    private static final String AUTHOR = "AUTHOR";
    private static final CreateMangaRequest CREATE_MANGA_REQUEST = new CreateMangaRequest(TITLE, AUTHOR);

    private final CreateMangaRequestAdapter createMangaRequestAdapter = mock(CreateMangaRequestAdapter.class);
    private final CreateMangaUseCase createMangaUseCase = mock(CreateMangaUseCase.class);
    private final MockMvc mockMvc =
            MockMvcBuilders
                    .standaloneSetup(new MangaController(createMangaUseCase, createMangaRequestAdapter))
                    .build();

    @Test
    void success() throws Exception {
        when(createMangaRequestAdapter.adapt(argThat(matches(aMangaDto())))).thenReturn(CREATE_MANGA_REQUEST);
        when(createMangaUseCase.execute(CREATE_MANGA_REQUEST)).thenReturn(Optional.of(CREATION_ID));

        MockHttpServletRequestBuilder request = post("/manga/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(aMangaDto()));

        mockMvc.perform(request)
                .andDo(log())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString(CREATION_ID)));
    }

    @Test
    void error() throws Exception {
        when(createMangaRequestAdapter.adapt(argThat(matches(aMangaDto())))).thenReturn(CREATE_MANGA_REQUEST);
        when(createMangaUseCase.execute(CREATE_MANGA_REQUEST)).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = post("/manga/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(aMangaDto()));

        mockMvc.perform(request)
                .andDo(log())
                .andExpect(status().isInternalServerError());
    }

    private MangaDto aMangaDto() {
        MangaDto mangaDto = new MangaDto();
        mangaDto.title = TITLE;
        mangaDto.author = AUTHOR;
        return mangaDto;
    }

    private String toJson(MangaDto mangaDto) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(mangaDto);
    }

}