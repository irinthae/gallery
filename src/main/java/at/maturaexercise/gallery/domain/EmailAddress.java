package at.maturaexercise.gallery.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailAddress(@Email @NotBlank String value) {
    //TODO Validation
}
