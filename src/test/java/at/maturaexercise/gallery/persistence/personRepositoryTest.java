package at.maturaexercise.gallery.persistence;

import at.maturaexercise.gallery.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static at.maturaexercise.gallery.TestFixtures.person;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class personRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    void ensureSaveAndReReadWorks() {
        Person person = person();

        var saved = personRepository.saveAndFlush(person);

        assertThat(saved).isSameAs(person);
        assertThat(saved.getId()).isNotNull();
    }

}