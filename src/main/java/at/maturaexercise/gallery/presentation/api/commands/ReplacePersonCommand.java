package at.maturaexercise.gallery.presentation.api.commands;

import jakarta.validation.constraints.NotBlank;

public record ReplacePersonCommand(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String nickname
) {
}
