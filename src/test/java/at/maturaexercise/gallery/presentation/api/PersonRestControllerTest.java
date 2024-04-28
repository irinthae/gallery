package at.maturaexercise.gallery.presentation.api;

import at.maturaexercise.gallery.domain.Person;
import at.maturaexercise.gallery.presentation.api.commands.CreatePersonCommand;
import at.maturaexercise.gallery.presentation.api.commands.ReplacePersonCommand;
import at.maturaexercise.gallery.service.PersonService;
import at.maturaexercise.gallery.service.UserAlreadyExistsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static at.maturaexercise.gallery.TestFixtures.person;
import static at.maturaexercise.gallery.presentation.api.RestFixtures.createPersonCommand;
import static at.maturaexercise.gallery.presentation.api.RestFixtures.replacePersonCommand;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonRestController.class)
class PersonRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PersonService personService;

    @BeforeEach
    void init() {
        assumeThat(mockMvc).isNotNull();
        assumeThat(personService).isNotNull();
    }

    @Test
    void ensureFetchPersonReturnsNotFoundForNonExistingId() throws Exception {
        long id = 777l;
        when(personService.fetchPerson(eq(id))).thenReturn(Optional.empty());

        var request = get(PersonRestController.ROUTE_ID, id).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
               .andExpect(status().isNotFound())
               .andDo(print());
    }

    @Test
    void ensureFetchPersonsReturnsOkForExistingData() throws Exception {
        Person person = person();
        when(personService.fetchPersons()).thenReturn(List.of(person));

        var request = get(PersonRestController.BASE_URL).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$[0].userName").value("bilbo.baggins@gdmail.at"))
               .andExpect(jsonPath("$[0].firstName").value("Bilbo"))
               .andExpect(jsonPath("$[0].lastName").value("Baggins"))
               .andExpect(jsonPath("$[0].nickname").value("Wyrmslayer"))
               .andDo(print());
    }

    @Test
    void ensureFetchPersonsReturnsOkForExistingDataWithValidLastNamePart() throws Exception {
        Person person = person();
        String lastNamePart = "b";
        when(personService.searchPersonsByLastNamePart(lastNamePart)).thenReturn(List.of(person));

        var request = get(PersonRestController.BASE_URL).param("lastNamePart", lastNamePart)
                                                        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$[0].userName").value("bilbo.baggins@gdmail.at"))
               .andExpect(jsonPath("$[0].firstName").value("Bilbo"))
               .andExpect(jsonPath("$[0].lastName").value("Baggins"))
               .andExpect(jsonPath("$[0].nickname").value("Wyrmslayer"))
               .andDo(print());
    }

    @Test
    void ensureFetchPersonsReturnsNoContentForMissingDataWithValidLastNamePart() throws Exception {
        Person person = person();
        String lastNamePart = "X";
        when(personService.searchPersonsByLastNamePart(lastNamePart)).thenReturn(Collections.emptyList());

        var request = get(PersonRestController.BASE_URL).param("lastNamePart", lastNamePart)
                                                        .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
               .andExpect(status().isNoContent())
               .andDo(print());
    }

    @Test
    void ensureFetchPersonsReturnsNoContentForMissingData() throws Exception {
        when(personService.fetchPersons()).thenReturn(Collections.emptyList());

        var request = get(PersonRestController.BASE_URL).accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
               .andExpect(status().isNoContent())
               .andDo(print());
    }

    @Test
    void ensureRegisterPersonReturnsOkForCorrectData() throws Exception {
        Person person = person();
        when(personService.register(
                eq(person.getUsername().value()),
                eq(person.getPassword()),
                eq(person.getFirstName()),
                eq(person.getLastName()),
                eq(person.getNickName()))).thenReturn(person);
        CreatePersonCommand cmd = createPersonCommand(person);

        var request = post(PersonRestController.BASE_URL).accept(MediaType.APPLICATION_JSON)
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .content(mapper.writeValueAsString(cmd));

        mockMvc.perform(request)
               .andExpect(status().isCreated())
               .andExpect(header().string(HttpHeaders.LOCATION, PersonRestController.BASE_URL))
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.userName").value("bilbo.baggins@gdmail.at"))
               .andExpect(jsonPath("$.firstName").value("Bilbo"))
               .andExpect(jsonPath("$.lastName").value("Baggins"))
               .andExpect(jsonPath("$.nickname").value("Wyrmslayer"))
               .andDo(print());
    }


    @Test
    void ensureRegisterUserWithExistingUsernameReturnsProperProblemDetailInABadRequestResponse() throws Exception {
        var username = "baggins@th.at";
        CreatePersonCommand cmd = createPersonCommand(username);
        when(personService.register(eq(username), any(), any(), any(), any())).thenThrow(UserAlreadyExistsException.forExistingUsername(username));

        var request = post(PersonRestController.BASE_URL).accept(MediaType.APPLICATION_JSON)
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .content(mapper.writeValueAsString(cmd));

        mockMvc.perform(request)
               .andExpect(status().isConflict())
               .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
               .andExpect(jsonPath("$.status").value(HttpStatus.CONFLICT.value()))
               .andExpect(jsonPath("$.title").value("UserRegistration"))
               .andDo(print());
    }

    @Test
    void ensureRegisterUserProperProblemDetailInAInternalServerErrorResponseDueToPersistenceException() throws Exception {
        var username = "baggins@th.at";
        CreatePersonCommand cmd = createPersonCommand(username);
        when(personService.register(eq(username), any(), any(), any(), any())).thenThrow(new PersistenceException("PersistenceException"));

        var request = post(PersonRestController.BASE_URL).accept(MediaType.APPLICATION_JSON)
                                                         .contentType(MediaType.APPLICATION_JSON)
                                                         .content(mapper.writeValueAsString(cmd));

        mockMvc.perform(request)
               .andExpect(status().isInternalServerError())
               .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON))
               .andExpect(jsonPath("$.status").value(HttpStatus.INTERNAL_SERVER_ERROR.value()))
               .andExpect(jsonPath("$.title").value("Persistence problems"))
               .andDo(print());
    }

    @Test
    void ensurePutPersonReturnsOkForCorrectData() throws Exception {
        Long id = 777L;
        Person person = spy(person());
        when(person.getId()).thenReturn(id);
        when(personService.replacePerson(eq(id), any(), any(), any(), any(), any())).thenReturn(person);
        ReplacePersonCommand cmd = replacePersonCommand(person);

        var request = put(PersonRestController.ROUTE_ID, id).accept(MediaType.APPLICATION_JSON)
                                                            .contentType(MediaType.APPLICATION_JSON)
                                                            .content(mapper.writeValueAsString(cmd));

        mockMvc.perform(request)
               .andExpect(status().isOk())
               .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               .andExpect(jsonPath("$.userName").value("bilbo.baggins@gdmail.at"))
               .andExpect(jsonPath("$.firstName").value("Bilbo"))
               .andExpect(jsonPath("$.lastName").value("Baggins"))
               .andExpect(jsonPath("$.nickname").value("Wyrmslayer"))
               .andDo(print());
    }

}









