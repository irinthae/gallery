package at.maturaexercise.gallery.persistence;

import at.maturaexercise.gallery.domain.Album;
import at.maturaexercise.gallery.domain.Photo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static at.maturaexercise.gallery.TestFixtures.album;
import static at.maturaexercise.gallery.TestFixtures.photo;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Test
    void ensureSaveAndReReadWorks() {
        Album album = album();
        Photo p1 = photo();
        Photo p2 = photo();
        album.addPhotos(p1, p2);

        var saved = albumRepository.saveAndFlush(album);

        assertThat(saved).isSameAs(album);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getOwner().getId()).isNotNull();
        assertThat(p1.getId()).isNotNull();
        assertThat(p2.getId()).isNotNull();
    }

    @Test
    void ensureFindAllByNameContainingIgnoreCaseWorks() {
        Album album1 = album("Album 1");
        Photo p1 = photo("1st Photo");
        Photo p2 = photo("2sn Photo");
        album1.addPhotos(p1, p2);
        Album album2 = album("Family Album 3");
        Photo p3 = photo("3rd Photo");
        Photo p4 = photo("4th Photo");
        album2.addPhotos(p3, p4);
        albumRepository.saveAll(List.of(album1, album2));

        List<Album> found = albumRepository.findAllByNameContainingIgnoreCase("family");

        assertThat(found).containsExactly(album2);
    }
}