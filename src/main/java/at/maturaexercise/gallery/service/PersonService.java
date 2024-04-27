package at.maturaexercise.gallery.service;

import at.maturaexercise.gallery.domain.Person;
import at.maturaexercise.gallery.persistence.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;

    public Person register(String username, String password, String firstName, String lastName, String nickName) {
        log.debug("Check if person '{}' exists in DB", username);
        var exists = personRepository.existsByUsername(username);

        if (exists) {
            log.warn("Person '{}' already exists -> throw Exception", username);
            throw new UserAlreadyExistsException(username);
        }

        Person person = Person.builder()
                              //TODO
                              .build();

        personRepository.save(person);
        log.info("Person '{} ({})' successfully saved", username, person.getId());

        String token = "abc"; //TODO
        log.info("Created confirmation token '{}' and linked it to user '{}'", token, username);

        //todo send confirmation email
        log.debug("Send registration confirmation email for user '{}'", username);

        return person;
    }
}
