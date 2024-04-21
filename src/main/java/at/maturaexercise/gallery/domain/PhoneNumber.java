package at.maturaexercise.gallery.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Embeddable
public class PhoneNumber {

    @Column(name = "countryCode")
    @NotNull
    @Positive
    private Integer countryCode;

    @Column(name = "areaCode")
    @NotNull
    @Positive
    private Integer areaCode;

    @Column(name = "serialNumber", length = 16)
    @NotNull
    @NotBlank
    private String serialNumber;
}
