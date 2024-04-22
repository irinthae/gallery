package at.maturaexercise.gallery.presentation.api.dtos;

import at.maturaexercise.gallery.domain.Album;

public record AlbumDto(String name, PhotographerDto owner, String type) {

    public AlbumDto(Album album) {
        this(album.getName(), new PhotographerDto(album.getOwner()), album.getType().name());
    }
}
