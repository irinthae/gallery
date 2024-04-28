package at.maturaexercise.gallery.service;

import lombok.Getter;

public class UserAlreadyExistsException extends RuntimeException {

    @Getter
    private final String username;

    private UserAlreadyExistsException(String username, String message) {
        super(message);
        this.username = username;
    }

    public static UserAlreadyExistsException forExistingUsername(String username) {
        String message = "Uswer with username %s already exists!".formatted(username);
        return new UserAlreadyExistsException(username, message);
    }
}
