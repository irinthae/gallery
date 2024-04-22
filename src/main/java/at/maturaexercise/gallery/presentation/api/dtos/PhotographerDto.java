package at.maturaexercise.gallery.presentation.api.dtos;

import at.maturaexercise.gallery.domain.Photographer;

public record PhotographerDto(String userName, String firstName, String lastName) {

    public PhotographerDto(Photographer photographer) {
        this(photographer.getUsername().value(), photographer.getFirstName(), photographer.getLastName());
    }
}
