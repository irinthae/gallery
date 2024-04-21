package at.maturaexercise.gallery.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "countries")
public class Country extends AbstractPersistable<Long> {

    @Column(name = "country_name", length = 64)
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "iso2Code", length = 2)
    @NotNull
    @NotBlank
    private String iso2Code;

    @Column(name = "topLevelDomain", length = 4)
    @NotNull
    @NotBlank
    private String topLevelDomain;

    @Column(name = "countryCode")
    @Positive
    @Min(1)
    @Max(999)
    private Integer countryCode;
}
