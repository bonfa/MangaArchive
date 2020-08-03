import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.fbonfadelli.manga.archive.MangaController;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateMangaIT {

    private static final String CREATION_ID = "CREATION_ID";

    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new MangaController()).build();

    @Test
    void name() throws Exception {
        MockHttpServletRequestBuilder request = post("/create")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(toJson(aCreateMangaRequest()));

        mockMvc.perform(request)
                .andDo(log())
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString(CREATION_ID)));
    }

    private CreateMangaRequest aCreateMangaRequest() {
        CreateMangaRequest createMangaRequest = new CreateMangaRequest();
        createMangaRequest.title = "TITLE";
        createMangaRequest.author = "AUTHOR";
        return createMangaRequest;
    }

    private String toJson(CreateMangaRequest createMangaRequest) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(createMangaRequest);
    }
}
