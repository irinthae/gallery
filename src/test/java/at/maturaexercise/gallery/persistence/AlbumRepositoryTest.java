package at.maturaexercise.gallery.persistence;

import at.maturaexercise.gallery.domain.Album;
import at.maturaexercise.gallery.domain.Photo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
}