package at.maturaexercise.gallery.service;

import at.maturaexercise.gallery.persistence.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static at.maturaexercise.gallery.TestFixtures.album;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlbumServiceTest {

    private AlbumService albumService;

    @Mock
    private AlbumRepository albumRepository;

    @BeforeEach
    void init() {
        assumeThat(albumRepository).isNotNull();
        albumService = new AlbumService(albumRepository);
    }

    @Test
    void ensureFetchAlbumsWithNoArgumentCallFindAllWorks() {
        Optional<String> searchCriteria = Optional.empty();
        var album = album();
        when(albumRepository.findAll()).thenReturn(List.of(album));

        var result = albumService.fetchAlbums(searchCriteria);

        assertThat(result).containsExactly(album);
        verify(albumRepository, times(1)).findAll();
        verifyNoMoreInteractions(albumRepository);
    }

    @Test
    void ensureFetchAlbumsWithArgumentCallFindAllByNameContainingIgnoreCaseWorks() {
        Optional<String> searchCriteria = Optional.of("Test");
        var album = album("Test Album");
        when(albumRepository.findAllByNameContainingIgnoreCase(any())).thenReturn(List.of(album));

        var result = albumService.fetchAlbums(searchCriteria);

        assertThat(result).containsExactly(album);
        verify(albumRepository, times(1)).findAllByNameContainingIgnoreCase(any());
        verifyNoMoreInteractions(albumRepository);
    }

}