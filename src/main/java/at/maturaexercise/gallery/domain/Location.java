package at.maturaexercise.gallery.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Location {

    @NotNull
    @Min(-180)
    @Max(180)
    private Double longitude;

    @NotNull
    @Min(-90)
    @Max(90)
    private Double latitude;

}
