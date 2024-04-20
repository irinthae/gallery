package at.maturaexercise.gallery.persistence;

import at.maturaexercise.gallery.domain.Photo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static at.maturaexercise.gallery.TestFixtures.photo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

@DataJpaTest
class photoRepositoryTest {

    @Autowired
    private PhotoRepository photoRepository;

    @Test
    void ensureSaveAndReReadWorks() {
        Photo photo = photo();
        assumeThat(photoRepository).isNotNull();

        var saved = photoRepository.saveAndFlush(photo);

        assertThat(saved).isNotNull().isSameAs(photo);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getPhotographer().getId()).isNotNull();
    }
}