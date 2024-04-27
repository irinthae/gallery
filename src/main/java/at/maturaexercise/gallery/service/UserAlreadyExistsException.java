package at.maturaexercise.gallery.service;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
    }
}
