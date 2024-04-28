package at.maturaexercise.gallery.service;

import lombok.Getter;

public class UserDoesNotExistsException extends RuntimeException {

    @Getter
    private final long id;

    private UserDoesNotExistsException(Long id, String message) {
        super(message);
        this.id = id;
    }

    public static UserDoesNotExistsException forId(Long id) {
        String message = "User with id %d does not exists".formatted(id);
        return new UserDoesNotExistsException(id, message);
    }
}
