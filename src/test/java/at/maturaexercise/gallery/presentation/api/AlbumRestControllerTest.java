package at.maturaexercise.gallery.presentation.api;

import at.maturaexercise.gallery.domain.Album;
import at.maturaexercise.gallery.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static at.maturaexercise.gallery.TestFixtures.album;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlbumRestController.class)
class AlbumRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;

    @BeforeEach
    void init() {

    }

    @Test
    void ensureGetApiAlbumsWorks() throws Exception {
        Album album = album();
        when(albumService.fetchAlbums(any())).thenReturn(List.of(album));

        var request = get("/api/albums").accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(request)
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$[0].name").value("Bilbo's Odyssey: A Tale of the Lonely Mountain"))
               .andExpect(jsonPath("$[0].type").value("PHYSICAL"))
               .andExpect(jsonPath("$[0].owner.lastName").value("Baggins"))
               .andDo(print());
    }

}