package at.maturaexercise.gallery.persistence;

import at.maturaexercise.gallery.domain.Photographer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static at.maturaexercise.gallery.TestFixtures.photographer;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PhotographerRepositoryTest {

    @Autowired
    private PhotographerRepository photographerRepository;

    @Test
    void ensureSaveAndReReadWorks() {
        Photographer photographer = photographer();

        var saved = photographerRepository.saveAndFlush(photographer);

        assertThat(saved).isSameAs(photographer);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getStudioAddress().getCountry().getId()).isNotNull();

    }

}