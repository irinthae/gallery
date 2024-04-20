package at.maturaexercise.gallery.domain;

import jakarta.persistence.*;
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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "photos")
public class Photo extends AbstractPersistable<Long> {

    @Column(name = "photo_name", length = 64)
    @NotBlank
    private String name;

    @Column(name = "description", length = 256)
    private String description;

    @Column(name = "location")
    @Embedded
    private Location location;

    @Column(name = "creationTimeStamp")
    @NotNull
    @PastOrPresent
    private LocalDateTime creationTimeStamp;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Photographer photographer;

    @PositiveOrZero
    private Integer width;

    @PositiveOrZero
    private Integer height;

    @Column(length = 1, columnDefinition = "CHAR(1) CHECK (orientation IN ('L', 'P', 'S'))")
    private Orientation orientation;

}
