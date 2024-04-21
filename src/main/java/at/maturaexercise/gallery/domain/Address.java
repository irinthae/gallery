package at.maturaexercise.gallery.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Embeddable
public class Address {
    public static final int LENGTH_STREET_NUMBER = 64;
    public static final int LENGTH_ZIP_CODE = 16;
    public static final int LENGTH_CITY = 64;

    @Column(name = "streetNumber", length = LENGTH_STREET_NUMBER, nullable = false)
    @NotBlank
    private String streetNumber;

    @Column(name = "zipCode", length = LENGTH_ZIP_CODE, nullable = false)
    @NotBlank
    private String zipCode;

    @Column(name = "city", length = LENGTH_CITY, nullable = false)
    @NotBlank
    private String city;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_address_2_countries"))
    @Valid
    private Country country;
}
