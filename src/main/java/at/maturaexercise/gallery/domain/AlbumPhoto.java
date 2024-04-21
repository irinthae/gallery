package at.maturaexercise.gallery.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Embeddable
public class AlbumPhoto implements Comparable<AlbumPhoto> {
    public static final int MIN_POSITION = 1;
    public static final int MAX_POSITION = 999;
    public static final Comparator<AlbumPhoto> positionComparator = Comparator.comparing(AlbumPhoto::getPosition);

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_album_photos_2_photos"))
    @NotNull
    @Valid
    private Photo photo;

    @NotNull
    @Min(MIN_POSITION)
    @Max(MAX_POSITION)
    private Integer position;

    @Override
    public int compareTo(AlbumPhoto rhs) {
        return positionComparator.compare(this, rhs);
    }
}
