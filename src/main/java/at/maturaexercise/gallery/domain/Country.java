package at.maturaexercise.gallery.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
    public static final int LENGTH_COUNTRY_NAME = 64;
    public static final int LENGTH_ISO_2_CODE = 2;
    public static final int LENGTH_TOP_LEVEL_DOMAIN = 4;
    public static final int MIN_COUNTRY_CODE = 1;
    public static final int MAX_COUNTRY_CODE = 999;

    @Column(name = "country_name", length = LENGTH_COUNTRY_NAME, nullable = false)
    @NotBlank
    private String name;

    @Column(name = "iso2Code", length = LENGTH_ISO_2_CODE, nullable = false)
    @NotBlank
    private String iso2Code;

    @Column(name = "topLevelDomain", length = LENGTH_TOP_LEVEL_DOMAIN, nullable = false)
    @NotBlank
    private String topLevelDomain;

    @Column(name = "countryCode")
    @Positive
    @Min(MIN_COUNTRY_CODE)
    @Max(MAX_COUNTRY_CODE)
    private Integer countryCode;
}
