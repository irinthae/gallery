package at.maturaexercise.gallery.presentation.api.commands;

import jakarta.validation.constraints.NotBlank;

public record UpdatePersonCommand(
        @NotBlank String username,
        @NotBlank String password,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String nickname
) {
}
