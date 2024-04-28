package at.maturaexercise.gallery.service;

import at.maturaexercise.gallery.domain.EmailAddress;
import at.maturaexercise.gallery.domain.Person;
import at.maturaexercise.gallery.persistence.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
public class PersonService implements DomainAttributeSupport {

    private final PersonRepository personRepository;

    @Transactional(readOnly = true)
    public Optional<Person> fetchPerson(Long id) {
        log.info("starting to fetch data for PersonService.fetchPerson(Long id) with id '{}'", id);
        return personRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Person> fetchPersons() {
        log.info("starting to fetch data for PersonService.fetchPersons");
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Person> searchPersonsByLastNamePart(String lastNamePart) {
        log.info("starting to fetch data for PersonService.searchPersons(String lastNamePart) with lastNamePart '{}'", lastNamePart);
        return personRepository.findByLastNameLikeIgnoreCase(lastNamePart);
    }

    public Person register(String username, String password, String firstName, String lastName, String nickName) {
        log.debug("Check if person '{}' exists in DB", username);
        var exists = personRepository.existsByUsername(username);

        if (exists) {
            log.warn("Person '{}' already exists -> throw Exception", username);
            throw UserAlreadyExistsException.forExistingUsername(username);
        }

        Person person = Person.builder()
                              .username(new EmailAddress(username))
                              .password(password)
                              .firstName(firstName)
                              .lastName(lastName)
                              .nickName(nickName)
                              .build();

        personRepository.save(person);
        log.info("Person '{} ({})' successfully saved", username, person.getId());

        String token = "abc"; //TODO
        log.info("Created confirmation token '{}' and linked it to user '{}'", token, username);

        //todo send confirmation email
        log.debug("Send registration confirmation email for user '{}'", username);

        return person;
    }

    public Person replacePerson(Long id, String username, String password, String firstName, String lastName, String nickname) {
        return personRepository.findById(id)
                               .map(person -> {
                                   //check username
                                   person.setUsername(new EmailAddress(username));
                                   person.setPassword(password);
                                   person.setFirstName(firstName);
                                   person.setLastName(lastName);
                                   person.setNickName(nickname);
                                   return person;
                               }).orElseGet(() -> register(username, password, firstName, lastName, nickname));
    }

    public Person updatePerson(Long id, String username, String password, String firstName, String lastName, String nickname) {
        return personRepository.findById(id)
                               .map(person -> {
                                   setAttributeIfNotBlank(username, (u) -> person.setUsername(new EmailAddress(u)));
                                   setAttributeIfNotBlank(password, person::setPassword);
                                   setAttributeIfNotBlank(firstName, person::setFirstName);
                                   setAttributeIfNotBlank(lastName, person::setLastName);
                                   setAttributeIfNotBlank(nickname, person::setNickName);
                                   return person;
                               }).orElseThrow(() -> UserDoesNotExistsException.forId(id));
    }
}









