package at.maturaexercise.gallery.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Embeddable
public class AlbumPhoto {
    public static final int MIN_POSITION = 1;
    public static final int MAX_POSITION = 999;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_album_photos_2_photos"))
    @NotNull
    @Valid
    private Photo photo;
    
}
