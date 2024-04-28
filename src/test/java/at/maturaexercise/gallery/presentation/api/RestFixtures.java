package at.maturaexercise.gallery.presentation.api;

import at.maturaexercise.gallery.domain.Person;
import at.maturaexercise.gallery.presentation.api.commands.CreatePersonCommand;
import at.maturaexercise.gallery.presentation.api.commands.ReplacePersonCommand;

public class RestFixtures {

    public static CreatePersonCommand createPersonCommand(Person person) {
        return new CreatePersonCommand(person.getUsername().value(), person.getPassword(), person.getFirstName(), person.getLastName(), person.getNickName());
    }

    public static CreatePersonCommand createPersonCommand(String username) {
        return new CreatePersonCommand(username, "geheim", "Bilbo", "Baggins", "Wyrmslayer");
    }

    public static ReplacePersonCommand replacePersonCommand(Person person) {
        return new ReplacePersonCommand(person.getUsername().value(), person.getPassword(), person.getFirstName(), person.getLastName(), person.getNickName());
    }

}
