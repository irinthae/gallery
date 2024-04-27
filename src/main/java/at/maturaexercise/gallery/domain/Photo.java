package at.maturaexercise.gallery.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDateTime;
import java.util.function.Predicate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "photos")
public class Photo extends AbstractPersistable<Long> {
    public static final int LENGTH_PHOTO_NAME = 64;
    public static final int LENGTH_DESCRIPTION = 256;
    public static final int LENGTH_ORIENTATION = 1;

    @Column(name = "photo_name", length = LENGTH_PHOTO_NAME, nullable = false)
    @NotBlank
    private String name;

    @Column(name = "description", length = LENGTH_DESCRIPTION)
    private String description;

    @Column(name = "location")
    @Embedded
    @Valid
    private Location location;

    @Column(name = "creationTimeStamp")
    @NotNull
    @PastOrPresent
    private LocalDateTime creationTimeStamp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_photos_2_photographers"))
    private Photographer photographer;

    @PositiveOrZero
    private Integer width;

    @PositiveOrZero
    private Integer height;

    @Column(length = LENGTH_ORIENTATION, columnDefinition = "CHAR(1) CHECK (orientation IN ('L', 'P', 'S'))")
    private Orientation orientation;

    public static final Predicate<Photo> hasCorrectOrientation = photo ->
            (photo.width > photo.height && photo.orientation == Orientation.LANDSCAPE) ||
            (photo.width < photo.height && photo.orientation == Orientation.PORTRAIT) ||
            (photo.width == photo.height && photo.orientation == Orientation.SQUARE);
}
