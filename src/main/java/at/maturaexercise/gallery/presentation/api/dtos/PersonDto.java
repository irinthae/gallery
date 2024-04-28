package at.maturaexercise.gallery.presentation.api.dtos;

import at.maturaexercise.gallery.domain.Person;

public record PersonDto(String userName, String firstName, String lastName, String nickname) {

    public PersonDto(Person person) {
        this(person.getUsername().value(), person.getFirstName(), person.getLastName(), person.getNickName());
    }
}
