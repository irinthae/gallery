package at.maturaexercise.gallery.presentation.api;

import at.maturaexercise.gallery.domain.Person;
import at.maturaexercise.gallery.presentation.api.commands.CreatePersonCommand;
import at.maturaexercise.gallery.presentation.api.commands.ReplacePersonCommand;
import at.maturaexercise.gallery.presentation.api.commands.UpdatePersonCommand;
import at.maturaexercise.gallery.presentation.api.dtos.PersonDto;
import at.maturaexercise.gallery.service.PersonService;
import at.maturaexercise.gallery.service.UserAlreadyExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor

@RestController
@RequestMapping(PersonRestController.BASE_URL)
public class PersonRestController {

    private static final String _SLASH = "/";
    private static final String PATH_VAR_ID = "{id}";
    protected static final String BASE_URL = "/api/persons";
    protected static final String PATH_ID = _SLASH + PATH_VAR_ID;
    protected static final String ROUTE_ID = BASE_URL + PATH_ID;

    private final PersonService personService;

    @GetMapping(PATH_ID)
    public ResponseEntity<PersonDto> fetchPerson(@PathVariable Long id) {
        return personService.fetchPerson(id)
                            .map(PersonDto::new)
                            .map(ResponseEntity::ok)
                            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> fetchPersons(@RequestParam Optional<String> lastNamePart) {
        List<Person> persons = lastNamePart.map(personService::searchPersonsByLastNamePart)
                                           .orElseGet(personService::fetchPersons);

        return persons.isEmpty()
               ? ResponseEntity.noContent().build()
               : ResponseEntity.ok(persons.stream()
                                          .map(PersonDto::new)
                                          .toList());
    }

    @PostMapping
    public ResponseEntity<PersonDto> createPerson(@RequestBody @Valid CreatePersonCommand cmd) {
        Person person = personService.register(cmd.username(), cmd.password(), cmd.firstName(), cmd.lastName(), cmd.nickname());
        // TODO
        URI location = URI.create("%s/%d".format(BASE_URL, person.getId()));

        return ResponseEntity.created(location).body(new PersonDto(person));
    }

    @PutMapping(PATH_ID)
    public ResponseEntity<PersonDto> replacePerson(@PathVariable Long id, @RequestBody ReplacePersonCommand cmd) throws URISyntaxException {
        Person person = personService.replacePerson(id, cmd.username(), cmd.password(), cmd.firstName(), cmd.lastName(), cmd.nickname());

        String location = new URI("%s/%d".formatted(BASE_URL, person.getId())).toString();

        return ResponseEntity.ok()
                             .header(HttpHeaders.LOCATION, location)
                             .body(new PersonDto(person));
    }

    @PatchMapping(PATH_ID)
    public ResponseEntity<PersonDto> updatePerson(@PathVariable Long id, @RequestBody UpdatePersonCommand cmd) throws URISyntaxException {
        Person person = personService.updatePerson(id, cmd.username(), cmd.password(), cmd.firstName(), cmd.lastName(), cmd.nickname());

        String location = new URI("%s/%d".formatted(BASE_URL, person.getId())).toString();

        return ResponseEntity.ok()
                             .header(HttpHeaders.LOCATION, location)
                             .body(new PersonDto(person));
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleUserAlreadyExistsException(UserAlreadyExistsException uaeEx) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, uaeEx.getMessage());
        problemDetail.setTitle("UserRegistration");
        problemDetail.setProperty("username", uaeEx.getUsername());

        return ResponseEntity.status(status).body(problemDetail);
    }
}
