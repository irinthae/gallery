package at.maturaexercise.gallery.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailAddress(@Email @NotNull @NotBlank String value) {
    //TODO Validation
}
